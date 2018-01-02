package com.hit.wangoceantao.algorithm;

import java.util.Iterator;

/**
 * Created by wanghaitao on 17/3/31.
 */
public class Stack<Item> implements Iterable<Item> {
    private Node first;
    private int count;


    private class Node {
        Item item;
        Node next;
    }

    public void push(Item item) {
        Node oldFirst = first;
        Node node = new Node();
        node.item = item;
        node.next = oldFirst;
        first = node;
        count++;

    }

    public Item pop() {
        Item item = first.item;
        first = first.next;
        count--;
        return item;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return count;
    }

    @Override
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        Node current;

        public ListIterator() {
            current = first;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    public static void main(String[] args) {
        Stack<String> stack = new Stack<>();
        stack.push("1");
        stack.push("2");
        stack.push("3");
        stack.push("4");
        for (String s : stack) {
            System.out.println(s);
        }

        System.out.println("count:" + stack.size());
    }

}
