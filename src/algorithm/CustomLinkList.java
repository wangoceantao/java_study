package algorithm;

import java.util.Iterator;
import java.util.Objects;

/**
 * Created by wanghaitao on 17/3/29.
 */
public class CustomLinkList<T> {
    public static class Node<T> {
        public T element;
        public Node<T> next;

        public Node(T element, Node next) {
            this.element = element;
            this.next = next;
        }
    }

    public Node first = null;

    public void add(T t) {
        if (first == null) {
            first = new Node(t, null);
        } else {
            Node node = new Node(t, first);
            first = node;
        }
    }

    public void remove(T t) {
        if (first == null) {
            return;
        }
        for (Node node = first; node != null; node = node.next) {
            if (Objects.equals(node.element, t)) {
                node.element = node.next == null ? null : node.next.element;
                node.next = node.next == null ? null : node.next.next;
                break;
            }
        }
    }

    public Iterator iterator() {
        return new LinkListIterator<T>();
    }

    public class LinkListIterator<T> implements Iterator<T> {
        private Node<T> current;

        public LinkListIterator() {
            current = first;
        }

        @Override
        public boolean hasNext() {
            return current != null && current.next != null;
        }

        @Override
        public T next() {
            current = current.next;
            return current.element;
        }
    }

    public static void main(String[] args) {
        CustomLinkList<Integer> linkList = new CustomLinkList();
        for (int index = 0; index < 15; index++) {
            linkList.add(index);
        }
        printLinkList(linkList);
        System.out.println("-- remove  --");
//        linkList.remove(5);
//        linkList.remove(4);
//        linkList.remove(3);
//        linkList.remove(2);
//        linkList.remove(1);
        linkList.remove(0);
        printLinkList(linkList);

    }

    private static void printLinkList(CustomLinkList linkList) {
        Iterator iterator = linkList.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }


}
