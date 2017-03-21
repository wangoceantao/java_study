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

package org.amino.ds.lockfree;

import java.util.AbstractQueue;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/**
 * This is a lock-free FIFO queue. The implementation is according to the paper
 * An Optimistic Approach to Lock-Free FIFO Queues by Edya Ladan-Mozes and Nir
 * Shavit
 *
 * @author Xiao Jun Dai
 *
 * @param <E> type of element in the queue
 */
public class LockFreeQueue<E> extends AbstractQueue<E> implements
        Queue<E> {
    private volatile Node<E> head, tail;

    private static final AtomicReferenceFieldUpdater<LockFreeQueue, Node> tailUpdater = AtomicReferenceFieldUpdater
            .newUpdater(LockFreeQueue.class, Node.class, "tail");
    private static final AtomicReferenceFieldUpdater<LockFreeQueue, Node> headUpdater = AtomicReferenceFieldUpdater
            .newUpdater(LockFreeQueue.class, Node.class, "head");

    /**
     * @param cmp expected value
     * @param val new value
     * @return true if cas is successful, otherwise false
     */
    private boolean casTail(Node<E> cmp, Node<E> val) {
        return tailUpdater.compareAndSet(this, cmp, val);
    }

    /**
     * @param cmp expected value
     * @param val new value
     * @return true if cas is successful, otherwise false
     */
    private boolean casHead(Node<E> cmp, Node<E> val) {
        return headUpdater.compareAndSet(this, cmp, val);
    }

    /**
     * Internal node definition of queue.
     *
     * @param <E> type of element in node
     */
    private static class Node<E> {
        E value;
        Node<E> next, prev;

        /**
         * default contructor.
         */
        public Node() {
            value = null;
            next = prev = null;
        }

        /**
         * @param val deafault value
         */
        public Node(E val) {
            value = val;
            next = prev = null;
        }

        /**
         * @param next default next pointer.
         */
        public Node(Node<E> next) {
            value = null;
            prev = null;
            this.next = next;
        }

        /**
         * @return next node
         */
        public Node<E> getNext() {
            return prev;
        }

        /**
         * @return value
         */
        public E getItem() {
            return value;
        }

    }

    /**
     * default constructor.
     */
    @SuppressWarnings("unchecked")
    public LockFreeQueue() {
        Node<E> dummy = new Node<E>();
        head = dummy;
        tail = dummy;
        // FIXME: is share dummy ok?
        // head = new AtomicReference<Node<E>>(dummy);
        // tail = new AtomicReference<Node<E>>(dummy);
    }

    /**
     * @param c collection
     */
    public LockFreeQueue(Collection<? extends E> c) {
        for (Iterator<? extends E> it = c.iterator(); it.hasNext();)
            offer(it.next());
    }

    /* (non-Javadoc)
     * @see java.util.AbstractCollection#isEmpty()
     */
    public boolean isEmpty() {
        return (head.value == null) && (tail.value == null);
        // or return first() == null;
    }

    /**
     * @param tail tail node
     * @param head head node
     */
    private void fixList(Node<E> tail, Node<E> head) {
        // System.out.println("fixlist");
        Node<E> curNode, curNodeNext, nextNodePrev;
        curNode = tail;
        while ((head == this.head) && (curNode != head)) {
            curNodeNext = curNode.next;
            // if (null == curNodeNext.prev) {
            // return; // ABA
            // }
            if (null == curNodeNext)
                System.out.println();
            nextNodePrev = curNodeNext.prev;
            if (nextNodePrev != curNode) {
                curNodeNext.prev = curNode;
            }
            curNode = curNodeNext;
        }
    }

    /**
       * {@inheritDoc}
       */
    @Override
    public Iterator<E> iterator() {
        return new QueueItr();
    }

    /**
     * iterator definition of queue.
     *
     */
    private class QueueItr implements Iterator<E> {
        private Node<E> nextNode;

        private E nextItem;

        private Node<E> lastRet;

        /**
         * default constructor.
         */
        QueueItr() {
            advance();
        }

        /**
         * Moves to next valid node and returns item to return for next(), or
         * null if no such.
         */
        private E advance() {
            lastRet = nextNode;
            E x = nextItem;

            Node<E> p = (nextNode == null) ? first() : nextNode.getNext();
            while (true) {
                if (p == null) {
                    nextNode = null;
                    nextItem = null;
                    return x;
                }
                E item = p.getItem();
                if (item != null) {
                    nextNode = p;
                    nextItem = item;
                    return x;
                } else
                    // skip over nulls
                    p = p.getNext();
            }
        }

        /**
          * {@inheritDoc}
          */
        public boolean hasNext() {
            return nextNode != null;
        }

        /**
          * {@inheritDoc}
          */
        public E next() {
            if (nextNode == null)
                throw new NoSuchElementException();
            return advance();
        }

        /**
          * {@inheritDoc}
          */
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    /**
      * {@inheritDoc}
      */
    @Override
    public int size() {
        int count = 0;
        Node<E> cur;
        for (cur = first(); cur != null && cur.value != null; cur = cur.prev) {
            if (++count == Integer.MAX_VALUE)
                break;
        }
        // if(cur != dummy) {
        // count++;
        // }
        return count;
    }

    /**
     * @return first node of queue
     */
    private Node<E> first() {
        Node<E> tail, head, fstNodePrev;
        Node<E> newDummy;
        E val;
        while (true) {
            head = this.head;
            tail = this.tail;
            fstNodePrev = head.prev;
            val = head.value;
            if (head == this.head) {
                if (val != null) {
                    if (tail != head) { // more than 1 node
                        if (null == fstNodePrev) {
                            fixList(tail, head);
                            continue;
                        }
                    } else { // Last Node in the queue, Figure 6.A
                        newDummy = new Node<E>(tail);
                        if (casTail(tail, newDummy)) {
                            head.prev = newDummy;
                        } else {
                            newDummy = null;
                        }
                        continue;
                    }
                    return head;
                } else { // head points to dummy, Figure 6.B
                    if (tail == head) {
                        return null;
                    } else {
                        if (null == fstNodePrev) {
                            fixList(tail, head);
                            continue;
                        }
                        casHead(head, fstNodePrev);
                    }
                }
            }
        }
    }

    /**
      * {@inheritDoc}
      */
    public boolean offer(E e) {
        if (e == null)
            throw new NullPointerException();
        Node<E> tail;
        Node<E> node = new Node<E>(e);
        while (true) {
            tail = this.tail;
            node.next = tail;
            if (casTail(tail, node)) {
                // Thread.yield();
                tail.prev = node;
                break;
            }
            try {
                Thread.sleep(10);
            } catch (Exception exp) {
            }

        }
        return true;
    }

    /* (non-Javadoc)
     * @see java.util.Queue#peek()
     */
    /**
     * {@inheritDoc}
     */
    public E peek() {
        Node<E> tail, head, fstNodePrev;
        Node<E> newDummy;
        E val;
        while (true) {
            head = this.head;
            tail = this.tail;
            fstNodePrev = head.prev;
            val = head.value;
            if (head == this.head) {
                if (val != null) {
                    if (tail != head) { // more than 1 node
                        if (null == fstNodePrev) {
                            fixList(tail, head);
                            continue;
                        }
                    } else { // Last Node in the queue, Figure 6.A
                        newDummy = new Node<E>(tail);
                        if (casTail(tail, newDummy)) {
                            head.prev = newDummy;
                        } else {
                            newDummy = null;
                        }
                        continue;
                    }
                    return val;
                } else { // head points to dummy, Figure 6.B
                    if (tail == head) {
                        return null;
                    } else {
                        if (null == fstNodePrev) {
                            fixList(tail, head);
                            continue;
                        }
                        casHead(head, fstNodePrev);
                    }
                }
            }
        }
    }

    /**
       * {@inheritDoc}
       */
    @SuppressWarnings("unchecked")
    public E poll() {
        Node<E> tail, head, fstNodePrev;
        Node<E> newDummy;
        E val;
        while (true) {
            head = this.head;
            tail = this.tail;
            fstNodePrev = head.prev;
            val = head.value;
            if (head == this.head) {
                if (val != null) {
                    if (tail != head) { // more than 1 node
                        if (null == fstNodePrev) {
                            fixList(tail, head);
                            continue;
                        }
                    } else { // Last Node in the queue, Figure 6.A
                        newDummy = new Node<E>(tail);
                        if (casTail(tail, newDummy)) {
                            head.prev = newDummy;
                        } else {
                            newDummy = null;
                        }
                        continue;
                    }
                    if (casHead(head, fstNodePrev)) {
                        // this.head.get().next = null; // free head
                        return val;
                    }
                } else { // head points to dummy, Figure 6.B
                    if (tail == head) {
                        return null;
                    } else {
                        if (null == fstNodePrev) {
                            fixList(tail, head);
                            continue;
                        }
                        casHead(head, fstNodePrev);
                    }
                }
            }
            try {
                Thread.sleep(10);
            } catch (Exception exp) {
            }

        }
    }

    /**
       * {@inheritDoc}
       */
    public boolean contains(Object o) {
        if (o == null)
            return false;
        for (Node<E> p = first(); p != null; p = p.getNext()) {
            E item = p.getItem();
            if (o.equals(item))
                return true;
        }
        return false;
    }

    /**
       * {@inheritDoc}
       */
    public Object[] toArray() {
        // Use ArrayList to deal with resizing.
        ArrayList<E> al = new ArrayList<E>();
        for (Node<E> p = first(); p != null; p = p.getNext()) {
            E item = p.getItem();
            al.add(item);
        }
        return al.toArray();
    }
}
