package design.proxy;

import design.ILawsuit;

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
