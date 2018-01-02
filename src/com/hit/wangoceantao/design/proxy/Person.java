package com.hit.wangoceantao.design.proxy;

import com.hit.wangoceantao.design.ILawsuit;

/**
 * Created by wanghaitao on 17/5/15.
 */
public class Person implements ILawsuit {
    final String TAG = Person.class.getSimpleName();

    @Override
    public void submit() {
        System.out.println(TAG + "-" + "submit");
    }
}
