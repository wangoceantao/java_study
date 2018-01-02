package com.hit.wangoceantao.model.masterworker;

/**
 * Created by zhangnannan on 16/11/6.
 */
public class PlusWork extends Worker {
    @Override
    public Object handle(Object input) {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Integer i = (Integer) input;
        return i;
    }
}
