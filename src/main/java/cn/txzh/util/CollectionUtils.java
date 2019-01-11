package cn.txzh.util;

import java.util.Collection;

/**
 * Created by leizhao_3 on 2019/1/7.
 */
public class CollectionUtils {

    public static boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.size() == 0;
    }

    public static boolean isNotEmpty(Collection<?> collection) {
        return !isEmpty(collection);
    }
}
