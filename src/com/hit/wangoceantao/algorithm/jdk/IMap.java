package com.hit.wangoceantao.algorithm.jdk;

import java.util.Iterator;

/**
 * Created by wanghaitao on 17/12/25.
 */
public interface IMap<K, V> {
    V put(K key, V value);

    V get(K key);

    Iterator iterator();
}
