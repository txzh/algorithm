package cn.txzh.util;

/**
 * Created by leizhao_3 on 2019/1/7.
 */
public class StringUtils {

    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

    public static boolean isNotEmpty(String str) {
        return !StringUtils.isEmpty(str);
    }
}
