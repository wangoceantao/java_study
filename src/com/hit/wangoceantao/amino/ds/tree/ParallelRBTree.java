/*
 * Copyright (c) 2007 IBM Corporation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hit.wangoceantao.amino.ds.tree;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.hit.wangoceantao.amino.pattern.internal.Doable;

/**
 * type of color attached on node in red-black tree.
 *
 */
enum Color {
    RED, BLACK
}

/**
 * type of request attached on node.
 *
 */
enum Request {
    UP_IN, UP_OUT, REMOVAL
}

/**
 * high performance parallel red-black tree. The implementation is based on The
 * performance of com.hit.wangoceantao.concurrent RB tree algorithms by Hanke, 1998
 *
 * @author Xiao Jun Dai
 *
 * @param <E>
 *            Type of element
 */
public class ParallelRBTree<E> {
    @SuppressWarnings("unchecked")
    public final static Node sentinel;
    static {
        sentinel = new Node();
        sentinel.left = sentinel.right = sentinel.p = sentinel;
    }

    /**
     * internal node of tree.
     *
     * @param <E>
     *            Type of element in node
     */
    public static class Node<E> {
        private Color color;
        private E value;
        private Node<E> left;
        private Node<E> right;
        private Node<E> p;

        /**
         * @return value
         */
        public E getValue() {
            return value;
        }

        List<Request> req;

        /**
         * default constructor.
         */
        Node() {
            color = Color.BLACK;
            value = null;
            left = sentinel;
            right = sentinel;
            p = sentinel;
            req = new CopyOnWriteArrayList<Request>();
        }

        /**
         * @param element element
         */
        public Node(E element) {
            color = Color.BLACK;
            value = element;
            left = sentinel;
            right = sentinel;
            p = sentinel;
            req = new CopyOnWriteArrayList<Request>();
        }

        /**
         * @param element element
         * @param parent parent
         */
        public Node(E element, Node<E> parent) {
            color = Color.BLACK;
            value = element;
            this.left = sentinel;
            this.right = sentinel;
            p = parent;
            req = new CopyOnWriteArrayList<Request>();
        }

        /**
          * {@inheritDoc}
          */
        public String toString() {
            return value.toString() + ((color == Color.BLACK) ? " B" : "R");
        }
    }

    private Node<E> root;

    private final RelaxedBalancer balancer;

    private Lock lock = new ReentrantLock();

    /**
     * default constructor.
     */
    @SuppressWarnings("unchecked")
    public ParallelRBTree() {
        root = sentinel;
        balancer = new RelaxedBalancer(lock);
    }

    /**
     * shutdown balancer.
     */
    public void shutdown() {
        balancer.shutdown();
    }

    /**
     * @param element element
     */
    public void insert(E element) {
        lock.lock();
        try {
            insert(new Node<E>(element));
        } finally {
            lock.unlock();
        }
    }

    /**
     * @param z node
     */
    @SuppressWarnings("unchecked")
    private void insert(Node<E> z) {
        Node<E> y = sentinel;
        Node<E> x = root;
        while (sentinel != x) {
            y = x;
            if (((Comparable) z.value).compareTo((Comparable) x.value) < 0) {
                x = x.left;
            } else {
                x = x.right;
            }
        }

        // locate position to insert
        if (y.req.contains(Request.REMOVAL)) {
            y.req.remove(Request.REMOVAL);
            y.value = z.value;
        } else {
            z.p = y;
            if (sentinel == y) { // empty tree
                root = z;
                root.color = Color.BLACK;
            } else {
                if (((Comparable) z.value).compareTo((Comparable) y.value) < 0) {
                    y.left = z;
                    y.right = new Node<E>(y.value, y);
                } else {
                    y.right = z;
                    y.left = new Node<E>(y.value, y);
                }

                if (y.req.contains(Request.UP_OUT)) {
                    y.req.remove(Request.UP_OUT);
                    y.color = Color.BLACK;
                } else {
                    /*
                     * parent of new internal node p is red (as well as p
                     * itself) then the resulting tree is no longer a standard
                     * rb tree
                     */
                    y.color = Color.RED;
                    y.req.add(Request.UP_IN);
                    balancer.addRequest(y);
                    // insertFixup(y);
                }
            }
        }
    }

    /**
     * @param element element
     * @return node removed
     */
    public Node<E> remove(E element) {
        lock.lock();
        try {
            // search the position first
            return remove(search(root, element));
        } finally {
            lock.unlock();
        }
    }

    /**
     * @param z start node
     * @return node removed
     */
    private Node<E> remove(Node<E> z) {
        if (sentinel == z) { // empty tree
            return sentinel;
        }

        assert ((sentinel == z.left) && (sentinel == z.right));

        Node<E> y = z.p;

        if (sentinel == y) {
            root = sentinel;
        } else if ((Color.RED == y.color) && (y.req.contains(Request.UP_IN))) {
            y.req.remove(Request.UP_IN);
            removeLeafAndParent(z, y);

            // if (Color.BLACK == y.color) {
            // x.req = Request.UP_OUT;
            // // removeFixup(x);
            // }
        } else {
            z.req.add(Request.REMOVAL);
            balancer.addRequest(z);
        }
        return z;
    }

    /**
     * @param z node expected to be removed
     * @param y parent node
     * @return node removed
     */
    private Node<E> removeLeafAndParent(Node<E> z, Node<E> y) {
        // remove leaf and its prarent
        Node<E> x = null; // remaining sibling
        if (z == y.left) { // z is left child
            x = y.right;
        } else {
            x = y.left;
        }
        x.p = y.p;
        if (y.p == sentinel) {
            root = x;
        } else {
            if (y == y.p.left) {
                y.p.left = x;
            } else {
                y.p.right = x;
            }
        }
        return x;
    }

    /**
     * @param x start node
     * @param operation operation done during walk
     */
    private void inorderWalk(Node<E> x, Doable<E, E> operation) {
        if (sentinel != x) {
            inorderWalk(x.left, operation);
            if (!(x.left == sentinel && x.right == sentinel))
                operation.run(x.value);
            inorderWalk(x.right, operation);
        }
    }

    /**
     * @param operation operation done during walk
     */
    public void inOrderWalk(Doable<E, E> operation) {
        inorderWalk(root, operation);
    }

    /**
     * @param k key
     * @return true if found, otherwise false
     */
    public boolean search(E k) {
        lock.lock();
        try {
            return search(root, k) == sentinel ? false : true;
        } finally {
            lock.unlock();
        }
    }

    /**
     * @param k key
     * @return node found
     */
    public Node<E> searchNode(E k) {
        lock.lock();
        try {
            return search(root, k);
        } finally {
            lock.unlock();
        }
    }

    /**
     * @param x start node
     * @param k key
     * @return node found
     */
    @SuppressWarnings("unchecked")
    private Node<E> search(Node<E> x, E k) {
        if (sentinel == x) { // empty tree
            return sentinel;
        }

        while (sentinel != x.left) {
            if (((Comparable) k).compareTo((Comparable) x.value) <= 0) {
                x = x.left;
            } else {
                x = x.right;
            }
        }

        if (x.value.equals(k) && !x.req.contains(Request.REMOVAL)) {
            if (x.req.size() > 0)
                System.out.println(x.req);
            return x;
        } else {
            return sentinel;
        }
    }

    /**
     * @param x start node
     * @return minimal node of the tree with x as root
     */
    private Node<E> min(Node<E> x) {
        while (sentinel != x.left) {
            x = x.left;
        }
        return x;
    }

    /**
     * @param x start node
     * @return minimal node of the tree with x as root
     */
    private Node<E> max(Node<E> x) {
        while (sentinel != x.right) {
            x = x.right;
        }
        return x;
    }

    /**
     * @param x start node
     * @return successor node of x
     */
    private Node<E> successor(Node<E> x) {
        if (sentinel != x.right) {
            return min(x.right);
        }
        Node<E> y = x.p;
        while ((sentinel != y) && (x == y.right)) {
            x = y;
            y = y.p;
        }
        return y;
    }

    /**
     * @param x start node
     * @return predeccessor node of x
     */
    private Node<E> predeccessor(Node<E> x) {
        if (sentinel != x.left) {
            return min(x.left);
        }
        Node<E> y = x.p;
        while ((sentinel != y) && (x == y.left)) {
            x = y;
            y = y.p;
        }
        return y;
    }

    /**
     * @return true if tree is red-black tree, otherwise false
     */
    public boolean verifyRBTree() {
        return verifyRBTree(root);
    }

    /**
     * @param x start node
     * @return true if tree start with x is red-black tree, otherwise false
     */
    private boolean verifyRBTree(Node<E> x) {
        if (sentinel != x) {
            if ((x.left == sentinel)) {
                if (x.color == Color.RED) {
                    System.out.println("leaf is red");
                    return false;
                }
            }

            if (x.color == Color.RED) {
                if (x.p.color == Color.RED) {
                    System.out.println("parent of red node " + x + " is red");
                    return false;
                }
            }

            return verifyRBTree(x.left) && verifyRBTree(x.right);
        }
        return true;
    }

    /**
     * worker doing relaxed balance action.
     *
     */
    private class RelaxedBalancer {
        private ExecutorService exec = Executors.newSingleThreadExecutor();
        private Lock lock;

        /**
         * @param lock lock for synchronization
         */
        public RelaxedBalancer(Lock lock) {
            this.lock = lock;
        }

        /**
         * shutdown executor.
         */
        public void shutdown() {
            exec.shutdown();
        }

        /**
         * @param node node
         * @return future object generated by executor
         */
        public Future<?> addRequest(final Node<E> node) {
            return exec.submit(new Callable<Boolean>() {
                public Boolean call() {
                    for (Request req : node.req)
                        switch (req) {
                        case REMOVAL:
                            // System.out.println("process request: Removel@"
                            // + node);
                            handleRemoval(node);
                            break;
                        case UP_IN:
                            handleUpIn(node);
                            // System.out.println("process request: UpIn@"
                            // + node);
                            break;
                        case UP_OUT:
                            // System.out
                            // .println("process request: UpOut@" + node);
                            handleUpOut(node);
                            break;
                        default:
                            return false;
                        }
                    return true;
                }
            });
        }

        /**
         * @param z node
         */
        private void handleRemoval(Node<E> z) {
            lock.lock();
            try {
                assert (z.req.contains(Request.REMOVAL));
                assert ((sentinel == z.left) && (sentinel == z.right));
                if (z.req.contains(Request.UP_OUT)) {
                    handleUpOut(z);
                }
                if (z.p.req.contains(Request.UP_OUT)) {
                    handleUpOut(z.p);
                }

                Node<E> y = z.p;
                Node<E> x = null; // remaining sibling
                if (z == y.left) { // z is left child
                    x = y.right;
                } else {
                    x = y.left;
                }
                if (x.req.contains(Request.UP_IN)) {
                    handleUpIn(x);
                }

                Node<E> sibling = removeLeafAndParent(z, y);
                if (Color.BLACK == y.color) {
                    if (Color.RED == sibling.color) {
                        sibling.color = Color.BLACK;
                    } else {
                        sibling.req.add(Request.UP_OUT);
                        balancer.addRequest(sibling);
                    }
                }
            } finally {
                lock.unlock();
            }
        }

        /**
         * @param z node
         */
        private void handleUpIn(Node<E> z) {
            lock.lock();
            try {
                // System.out.println("handleUpIn");
                assert (z.req.contains(Request.UP_IN));
                z.req.remove(Request.UP_IN);
                Node<E> y = null; // sibling of parent

                if (root == z.p) {
                    root.color = Color.RED;
                    return;
                }

                if (Color.RED == z.p.color) {
                    if (z.p == z.p.p.left) { // parent is left child
                        y = z.p.p.right;
                        if (Color.RED == y.color) {
                            // red sibling, shift request up, case 2.e
                            z.p.color = Color.BLACK;
                            y.color = Color.BLACK;
                            z.p.p.color = Color.RED;
                            z.p.p.req.add(Request.UP_IN);
                            balancer.addRequest(z.p.p);
                        } else {
                            if (z == z.p.right) { // rotation, case 2.d
                                z = z.p;
                                leftRotate(z);
                            }
                            z.p.color = Color.BLACK; // double rotation, case
                            // 2.c
                            z.p.p.color = Color.RED;
                            rightRotate(z.p.p);
                        }
                    } else { // same as if clause with "right" and "left"
                        // exchanged
                        y = z.p.p.left;
                        if (Color.RED == y.color) {
                            z.p.color = Color.BLACK;
                            y.color = Color.BLACK;
                            z.p.p.color = Color.RED;
                            z.p.p.req.add(Request.UP_IN);
                            balancer.addRequest(z.p.p);
                        } else {
                            if (z == z.p.left) {
                                z = z.p;
                                rightRotate(z);
                            }
                            z.p.color = Color.BLACK;
                            z.p.p.color = Color.RED;
                            leftRotate(z.p.p);
                        }
                    }
                }
            } finally {
                lock.unlock();
            }
        }

        /**
         * @param x node
         */
        private void handleUpOut(Node<E> x) {
            lock.lock();
            try {
                assert (x.req.contains(Request.UP_OUT));
                x.req.remove(Request.UP_OUT);
                Node<E> w = null;
                // while ((x != root) && (Color.BLACK == x.color)) {
                if ((x != root) && (Color.BLACK == x.color)) {
                    if (x == x.p.left) {
                        w = x.p.right;
                        if (Color.RED == w.color) { // case 4.a
                            w.color = Color.BLACK;
                            x.p.color = Color.RED;
                            leftRotate(x.p);
                            w = x.p.right;
                        }
                        if ((Color.BLACK == w.left.color) // case 4.b
                                && (Color.BLACK == w.right.color)) {
                            w.color = Color.RED;
                            if (Color.RED == x.p.color) {
                                x.p.color = Color.BLACK;
                            } else {
                                x.p.req.add(Request.UP_OUT);
                                balancer.addRequest(x.p);
                            }
                        } else {
                            if (Color.BLACK == w.right.color) { // case 4.d
                                w.left.color = Color.BLACK;
                                w.color = Color.RED;
                                rightRotate(w);
                                w = x.p.right;
                            }
                            w.color = x.p.color; // case 4.c
                            x.p.color = Color.BLACK;
                            w.right.color = Color.BLACK;
                            leftRotate(x.p);
                            x = root;
                        }
                    } else { // same as if clause with "right" and "left"
                        // exchanged
                        w = x.p.left;
                        if (Color.RED == w.color) {
                            w.color = Color.BLACK;
                            x.p.color = Color.RED;
                            rightRotate(x.p);
                            w = x.p.left;
                        }
                        if ((Color.BLACK == w.right.color)
                                && (Color.BLACK == w.left.color)) {
                            w.color = Color.RED;
                            if (Color.RED == x.p.color) {
                                x.p.color = Color.BLACK;
                            } else {
                                x.p.req.add(Request.UP_OUT);
                                balancer.addRequest(x.p);
                            }
                        } else {
                            if (Color.BLACK == w.left.color) {
                                w.right.color = Color.BLACK;
                                w.color = Color.RED;
                                leftRotate(w);
                                w = x.p.left;
                            }
                            w.color = x.p.color;
                            x.p.color = Color.BLACK;
                            w.left.color = Color.BLACK;
                            rightRotate(x.p);
                            x = root;
                        }
                    }
                }
                x.color = Color.BLACK;
            } finally {
                lock.unlock();
            }
        }

        /**
         * Addtional transformation for tuning.
         *
         * @param x
         */
        private void handleUpOutUpOut(Node<E> x) {
            Node<E> y = x.p;
            assert (y.left.req.contains(Request.UP_OUT) && y.right.req
                    .contains(Request.UP_OUT));
            y.left.req.remove(Request.UP_OUT);
            y.right.req.remove(Request.UP_OUT);

            if (Color.RED == y.color) {
                y.color = Color.BLACK;
            } else {
                y.req.add(Request.UP_OUT);
                balancer.addRequest(y);
            }
        }

        /**
         * @param z start node
         * @param y parent node
         * @return node removed
         */
        private Node<E> removeLeafAndParent(Node<E> z, Node<E> y) {
            // remove leaf and its prarent
            Node<E> x = null; // remaining sibling
            if (z == y.left) { // z is left child
                x = y.right;
            } else {
                x = y.left;
            }
            x.p = y.p;
            if (y.p == sentinel) {
                root = x;
            } else {
                if (y == y.p.left) {
                    y.p.left = x;
                } else {
                    y.p.right = x;
                }
            }
            return x;
        }

        /**
         * @param x node
         */
        private void leftRotate(Node<E> x) {
            Node<E> y = x.right;
            x.right = y.left;
            if (y.left != sentinel) {
                y.left.p = x;
            }
            y.p = x.p;
            if (x.p == sentinel) {
                root = y;
            } else {
                if (x == x.p.left) {
                    x.p.left = y;
                } else {
                    x.p.right = y;
                }
            }
            y.left = x;
            x.p = y;
        }

        /**
         * @param x node
         */
        private void rightRotate(Node<E> x) {
            Node<E> y = x.left;
            x.left = y.right;
            if (y.right != sentinel) {
                y.right.p = x;
            }
            y.p = x.p;
            if (x.p == sentinel) {
                root = y;
            } else {
                if (x == x.p.right) {
                    x.p.right = y;
                } else {
                    x.p.left = y;
                }
            }
            y.right = x;
            x.p = y;
        }
    }
}
