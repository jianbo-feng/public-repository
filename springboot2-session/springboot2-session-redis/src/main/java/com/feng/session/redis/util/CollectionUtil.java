package com.feng.session.redis.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CollectionUtil {

    /**
     * Iterable转换为List
     * @param iterable
     * @return
     */
    public static List<?> iterableToList(Iterable<?> iterable) {
        List list = null;
        if(iterable != null) {
            list = new ArrayList();
            Iterator iterator = iterable.iterator();
            while (iterator.hasNext()) {
                list.add(iterator.next());
            }
        }
        return list;
    }
}
