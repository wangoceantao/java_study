package com.hit.wangoceantao.algorithm;

/**
 * Created by wanghaitao on 18/1/2.
 * 二叉查找树
 */
public class BST<KEY extends Comparable<KEY>, VALUE> {
    private static class Node<KEY extends Comparable<KEY>, VALUE> {
        private KEY key;
        private VALUE value;
        private Node<KEY, VALUE> left;
        private Node<KEY, VALUE> right;
        private int size;

        public Node(KEY key, VALUE value, int size) {
            this.key = key;
            this.value = value;
            this.size = size;
        }
    }

    private Node<KEY, VALUE> root;

    public int size() {
        return size(root);
    }

    private int size(Node<KEY, VALUE> root) {
        if (root == null) {
            return 0;
        }
        return root.size;
    }

    public void put(KEY key, VALUE value) {
        root = put(root, key, value);
    }

    private Node<KEY, VALUE> put(Node<KEY, VALUE> node, KEY key, VALUE value) {
        if (node == null) {
            return new Node<>(key, value, 1);
        }
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = put(node.left, key, value);
        } else if (cmp > 0) {
            node.right = put(node.right, key, value);
        } else {
            node.value = value;
        }

        node.size = size(node.left) + size(node.right) + 1;

        return node;
    }

    private VALUE get(KEY key) {
        return get(root, key);
    }

    private VALUE get(Node<KEY, VALUE> node, KEY key) {
        if (node == null) {
            return null;
        }
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            return get(node.left, key);
        } else if (cmp > 0) {
            return get(node.right, key);
        }
        return node.value;
    }

    public VALUE min() {
        Node<KEY, VALUE> min = min(root);
        if (min == null) {
            return null;
        }
        return min.value;
    }

    private Node<KEY, VALUE> min(Node<KEY, VALUE> node) {
        if (node == null) {
            return null;
        }
        if (node.left == null) {
            return node;
        }
        return min(node.left);
    }

    public void delMin() {
        root = delMin(root);
    }

    private Node<KEY, VALUE> delMin(Node<KEY, VALUE> node) {

        if (node == null) {
            return null;
        }
        if (node.left == null) {
            return node.right;
        } else {
            node.left = delMin(node.left);
            node.size = size(node.left) + size(node.right) + 1;
            return node;
        }
    }

    public void delete(KEY key) {
        root = delete(root, key);
    }

    private Node<KEY, VALUE> delete(Node<KEY, VALUE> node, KEY key) {
        if (node == null) {
            return null;
        }

        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = delete(node.left, key);
        } else if (cmp > 0) {
            node.right = delete(node.right, key);
        } else {
            if (node.right == null) {
                return node.left;
            }
            if (node.left == null) {
                return node.right;
            }
            Node<KEY, VALUE> tmp = node;
            node = min(tmp.right);
            node.right = delMin(tmp.right);
            node.left = tmp.left;
        }
        node.size = size(node.left) + size(node.right) + 1;
        return node;
    }

    public static void main(String[] args) {
        BST<Integer, String> bst = new BST<>();
        bst.put(1, "wang");
        bst.put(3, "hai");
        bst.put(5, "tao");
        bst.put(10, "is");

        System.out.println("key:1" + ";value:" + bst.get(1));
        System.out.println("key:2" + ";value:" + bst.get(2));
        System.out.println("key:3" + ";value:" + bst.get(3));
        System.out.println("key:4" + ";value:" + bst.get(4));
        System.out.println("key:5" + ";value:" + bst.get(5));
        System.out.println("size:" + bst.size());
        System.out.println("min:" + bst.min());
        bst.delete(1);
        bst.delete(2);
        System.out.println("min:" + bst.min());
    }


}
