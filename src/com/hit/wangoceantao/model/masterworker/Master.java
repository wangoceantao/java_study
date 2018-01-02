package com.hit.wangoceantao.model.masterworker;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CountDownLatch;

/**
 * Created by zhangnannan on 16/11/6.
 */
public class Master {

    private Queue<Object> workQuene = new ConcurrentLinkedQueue<>();
    private Map<String, Thread> threadMap = new HashMap<>();
    private Map<String, Object> resultMap = new ConcurrentHashMap<>();
    private CountDownLatch countDownLatch;

    public boolean isComplete() {
//        for (Map.Entry<String, Thread> entry : threadMap.entrySet()) {
//            if (entry.getValue().getState() != Thread.State.TERMINATED) {
//                return false;
//            }
//        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return true;
    }

    public void submit(Object job) {
        workQuene.add(job);
    }

    public void execute() {
        for (Map.Entry<String, Thread> entry : threadMap.entrySet()) {
            entry.getValue().start();
        }
    }

    public Map<String, Object> getResultMap() {
        return resultMap;
    }

    public Master(Worker worker, int threadCount) {
        worker.setResultMap(resultMap);
        worker.setWorkerQuene(workQuene);
        countDownLatch = new CountDownLatch(threadCount);
        for (int index = 0; index < threadCount; index++) {
            threadMap.put(Integer.toString(index), new ThreadWithCountDown(worker, countDownLatch));
        }
    }

    private class ThreadWithCountDown extends Thread {
        Runnable target;
        CountDownLatch countDownLatch;

        public ThreadWithCountDown(Runnable target, CountDownLatch countDownLatch) {
            this.target = target;
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {
            target.run();
            countDownLatch.countDown();
        }
    }

    public static void main(String[] args) {
        Master master = new Master(new PlusWork(), 1000);
        for (int index = 1; index <= 10000; index++) {
            master.submit(Integer.valueOf(index));
        }
        master.execute();
        long startTime = System.currentTimeMillis();
        int result = 0;
//        Map<String, Object> resultMap = master.getResultMap();
//        while (resultMap.size() > 0 || !master.isComplete()) {
//            Set<String> keySet = resultMap.keySet();
//            String key = null;
//            for (String value : keySet) {
//                key = value;
//                break;
//            }
//            Object re = null;
//            if (key != null) {
//                re = resultMap.get(key);
//            }
//            if (re != null) {
//                result += (Integer) re;
//            }
//            if (key != null) {
//                resultMap.remove(key);
//            }
//
//        }
        if (master.isComplete()) {
            Map<String, Object> resultMap = master.getResultMap();
            Set<String> keySet = resultMap.keySet();
            for (String value : keySet) {
                Object re = resultMap.get(value);
                result += (Integer) re;
            }
        }
        System.out.println("cast time:" + (System.currentTimeMillis() - startTime));
        System.out.println("result:" + result);
    }


}
