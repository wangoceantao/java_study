package algorithm;

import java.util.Iterator;
import java.util.Objects;

/**
 * Created by wanghaitao on 17/3/29.
 */
public class CustomLinkList<T extends Comparable<T>> {
    int size;

    public static class Node<T> {
        public T element;
        public Node<T> next;

        public Node(T element, Node next) {
            this.element = element;
            this.next = next;
        }
    }

    public Node<T> head = null;
    public Node<T> last = null;

    public Node<T> getHead() {
        return head;
    }

    public void add(T t) {
        if (head == null) {
            head = new Node(t, null);
            last = head;
        } else {
            Node node = new Node(t, null);
            last.next = node;
            last = node;
        }
        size++;
    }

    public void remove(T t) {
        if (head == null) {
            return;
        }
        for (Node node = head; node != null; node = node.next) {
            if (Objects.equals(node.element, t)) {
                node.element = node.next == null ? null : node.next.element;
                node.next = node.next == null ? null : node.next.next;
                size--;
                break;
            }
        }

    }

    public int size() {
        return size;
    }

    public Node node(int index) {
        Node node = head;
        for (int pos = 0; pos < index; pos++) {
            node = node.next;
        }
        return node;
    }

    public CustomLinkList reverse() {
        if (head == null || head.next == null) {
            return this;
        }
        Node pre, cur, next;
        cur = head;
        pre = next = null;
        while (cur != null) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        head = pre;
        return this;
    }

    public static <T extends Comparable<T>> CustomLinkList<T> merge(CustomLinkList<T> first, CustomLinkList<T> second) {
        CustomLinkList<T> result = new CustomLinkList<>();
        if (first == null || first.size() == 0) {
            result.head = second.getHead();
            return result;
        }
        if (second == null || second.size() == 0) {
            result.head = first.getHead();
            return result;
        }

        Node<T> head = null;
        Node<T> p1 = null;
        Node<T> p2 = null;
        if (first.getHead().element.compareTo(second.getHead().element) < 0) {
            head = first.getHead();
            p1 = first.getHead().next;
            p2 = second.getHead();

        } else {
            head = second.getHead();
            p1 = first.getHead();
            p2 = second.getHead().next;
        }
        Node<T> cur = head;
        while (p1 != null && p2 != null) {
            if (p1.element.compareTo(p2.element) <= 0) {
                cur.next = p1;
                cur = p1;
                p1 = p1.next;
            } else {
                cur.next = p2;
                cur = p2;
                p2 = p2.next;
            }
        }
        if (p1 != null) {
            cur.next = p1;
        } else {
            cur.next = p2;
        }
        result.head = head;
        return result;

    }

    private static boolean compare(Comparable o1, Comparable o2) {
        return o1.compareTo(o2) <= 0;
    }

    public static <T extends Comparable<T>> boolean hasCircle(CustomLinkList<T> linkList) {
        if (linkList == null || linkList.getHead() == null) {
            return false;
        }

        Node<T> fast;
        Node<T> slow;
        fast = slow = linkList.getHead();
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow.element.compareTo(fast.element) == 0) {
                return true;
            }
        }
        return false;
    }

    public static <T extends Comparable<T>> int getCycleLength(CustomLinkList<T> linkList) {
        Node<T> startNode = getCircleStartPoint(linkList);
        if (startNode == null) {
            return 0;
        }

        int cycleLength = 0;
        Node<T> cur = startNode;
        while (cur != null) {
            cur = cur.next;
            cycleLength++;
            if (cur.element.compareTo(startNode.element) == 0) {
                return cycleLength;
            }
        }
        return 0;
    }

    public static <T extends Comparable<T>> Node getCircleStartPoint(CustomLinkList<T> linkList) {
        if (linkList == null || linkList.getHead() == null) {
            return null;
        }

        Node<T> fast;
        Node<T> slow;
        fast = slow = linkList.getHead();
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow.element.compareTo(fast.element) == 0) {
                return slow;
            }
        }
        return null;
    }


    public Iterator iterator() {
        return new LinkListIterator<T>(0);
    }

    public class LinkListIterator<T> implements Iterator<T> {
        private Node<T> next;
        private Node<T> lastReturned = null;
        private int nextIndex;

        public LinkListIterator(int index) {
            next = node(index);
            nextIndex = index;
        }

        @Override
        public boolean hasNext() {
            return next != null;
        }

        @Override
        public T next() {
            lastReturned = next;
            next = next.next;
            nextIndex++;
            return lastReturned.element;
        }
    }

    public static void main(String[] args) {
//        reverseTest();
//        mergeTest();
        circleTest();
        reversePrintTest();
    }

    private static void reversePrintTest() {
        CustomLinkList<Integer> linkList = new CustomLinkList();
        linkList.add(0);
        linkList.add(1);
        linkList.add(2);
        linkList.add(3);
        linkList.add(4);
        linkList.add(5);
        System.out.println("reversePrintTest source");
        printLinkList(linkList);
        System.out.println("reversePrint result");
        reversePrint(linkList.getHead());
        System.out.println("reversePrintByStack result");
        reversePrintByStack(linkList.getHead());
    }

    private static void circleTest() {
        CustomLinkList<Integer> linkList = new CustomLinkList();
        linkList.add(1);
        linkList.add(2);
        linkList.add(3);
        linkList.add(4);
        linkList.add(5);
        linkList.add(3);
        linkList.add(4);
        System.out.println("circle test source");
        printLinkList(linkList);
        System.out.println("circle test result");
        System.out.println("hasCircle:" + hasCircle(linkList));
        System.out.println("cycle length:" + getCycleLength(linkList));
    }

    private static void reverseTest() {
        CustomLinkList<Integer> linkList = new CustomLinkList();
        linkList.add(1);
        linkList.add(2);
        linkList.add(3);
        linkList.add(4);
        linkList.add(5);
        System.out.println("reverse source");
        printLinkList(linkList);
        System.out.println("reverse result");
        printLinkList(linkList.reverse());
    }

    private static void mergeTest() {
        CustomLinkList<Integer> headLinkList = new CustomLinkList();
        headLinkList.add(1);
        headLinkList.add(3);
        headLinkList.add(5);
        CustomLinkList<Integer> secondLinkList = new CustomLinkList();
        secondLinkList.add(2);
        secondLinkList.add(4);
        secondLinkList.add(6);
        System.out.println("merge head LinkList ");
        printLinkList(headLinkList);
        System.out.println("merge second LinkList ");
        printLinkList(secondLinkList);
        CustomLinkList merge = CustomLinkList.merge(headLinkList, secondLinkList);
        System.out.println("merge LinkList start");
        printLinkList(merge);
        System.out.println("merge LinkList end");
    }

    private static void printLinkList(CustomLinkList linkList) {
        Iterator iterator = linkList.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    public static <T> void reversePrint(Node<T> head) {
        if (head == null) {
            return;
        }
        reversePrint(head.next);
        System.out.println(head.element);
    }

    public static <T> void reversePrintByStack(Node<T> head) {
        if (head == null) {
            return;
        }

        Stack<T> stack = new Stack<>();
        Node<T> cur = head;
        while (cur != null) {
            stack.push(cur.element);
            cur = cur.next;
        }
        while (!stack.isEmpty()) {
            System.out.println(stack.pop());
        }
    }

}
