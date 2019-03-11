package com.hzau.feidian.hzauaudiobook.share;

import java.io.Serializable;

/**
 * @author 项三六
 * @time 2019/3/11 21:19
 * @comment
 */

public class Pair<K, V> implements Serializable {

    private K key;

    private V value;

    public K getKey() {
        return this.key;
    }

    public V getValue() {
        return this.value;
    }

    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public String toString() {
        return key + " = " + value;
    }
}
