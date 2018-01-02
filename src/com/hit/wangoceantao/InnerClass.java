package com.hit.wangoceantao;

public class InnerClass {
    public static String name = "com/hit/wangoceantao/test";
    private InnerCallback callback;

    public static void main(String[] args) {
        int count = 1;
        InnerClass innerClass = new InnerClass();
        innerClass.setCallback(new InnerCallback() {
            @Override
            public void callback() {
                System.out.println(count);
            }
        });
        innerClass.handleCallback();
    }
    public void setCallback(InnerCallback callback) {
        this.callback = callback;

    }

    private void handleCallback() {
        callback.callback();
    }

    public interface InnerCallback {
        void callback();
    }
}
