package jni;

/**
 * Created by wanghaitao on 17/5/23.
 */
public class Hello {
    native void print(String input);
    public static void main(String[] args){
        Hello hello=new Hello();
        hello.print("test");
    }
}
