package com.hit.wangoceantao;

/**
 * Created by wanghaitao on 16/8/26.
 */
public class Pair<T> {
    private T name;

    public Pair() {

    }

    public Pair(T name) {
        this.name = name;
    }

    public T getName() {
        return name;
    }

    public void setName(T name) {
        this.name = name;
    }
}
