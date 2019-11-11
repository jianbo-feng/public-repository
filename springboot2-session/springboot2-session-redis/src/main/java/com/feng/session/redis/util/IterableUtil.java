package com.feng.session.redis.util;

import java.util.Collection;
import java.util.Iterator;

/**
 * Iterable工具类
 * @author Feng
 * @param <T>
 */
public class IterableUtil<T> implements Iterable<T> {

    private Collection<T> collection;

    public IterableUtil(Collection<T> collection) {
        this.collection = collection;
    }

    //    @Override
    public Iterator<T> iterator() {
        return collection.iterator();
    }

}
