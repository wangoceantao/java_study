package com.hit.wangoceantao;

import java.util.Random;

/**
 * @Description: ${TODO}
 * <p/>
 * Created by wanghaitao on 16/4/29 22:18.
 * <p/>
 * Email：wanghaitao@leomaster.com
 */
public class TestDataGenerator {
    private static TestDataGenerator sInstance;

    public static TestDataGenerator getInstance() {
        if (sInstance == null) {
            synchronized (TestDataGenerator.class) {
                if (sInstance == null) {
                    sInstance = new TestDataGenerator();
                }
            }
        }
        return sInstance;
    }

    public int[] getArrayData() {
        int length = 10;
        int[] data = new int[length];
        Random random = new Random();
        for (int index = 0; index < length; index++) {
            data[index] = (random.nextInt(100));
        }
        return data;
    }
//    public Integer[] getArrayData() {
//        int length = 30;
//
//        HashSet<Integer> hashSet = new HashSet<>();
//        Random random = new Random();
//        for (int index = 0; index < length; index++) {
//            hashSet.add(random.nextInt(100));
//        }
//        Integer[] data=new Integer[hashSet.size()];
//        Iterator iterator = hashSet.iterator();
//        int index=0;
//        while (iterator.hasNext()) {
//            data[index]=(Integer) iterator.next();
//        }
//        return data;
//    }
}
