package com.hit.wangoceantao.concurrent;

/**
 * Created by zhangnannan on 16/11/26.
 */
public class BoundedHashSetTest {
    public static void main(String[] args) {
        BoundedHashSet set = new BoundedHashSet(5);
        new Thread(new AddRunnable(set), "ADD THREAD").start();
        new Thread(new RemoveRunnable(set), "REMOVE THREAD").start();
    }

    private static class AddRunnable implements Runnable {

        private BoundedHashSet<Integer> boundedHashSet;
        private int count = 0;

        public AddRunnable(BoundedHashSet set) {
            this.boundedHashSet = set;

        }

        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(100);
                    String addString = Thread.currentThread().getName() + "-" + count;
                    boundedHashSet.add(count);
                    System.out.println(addString);
                    count++;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    private static class RemoveRunnable implements Runnable {

        private BoundedHashSet<Integer> boundedHashSet;
        private int count = 0;

        public RemoveRunnable(BoundedHashSet set) {
            this.boundedHashSet = set;

        }

        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(1000);
                    String addString = Thread.currentThread().getName() + "-" + count;
                    boundedHashSet.remove(count);
                    System.out.println(addString);
                    count++;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
