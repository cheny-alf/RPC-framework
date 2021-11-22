package com.cheny.rpc.common.util;

import org.apache.commons.lang3.StringUtils;

/**
 * @ClassName Stringutil
 * @Description
 * @Author cheny
 * @Date 2021/11/21 15:29
 * @Version 1.0
 **/
public class StringUtil {
    public static boolean isEmpty(String str) {
        if (str != null) {
            str = str.trim();
        }
        return StringUtils.isEmpty(str);
    }

    /**
     * 判断字符串是否非空
     */
    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    /**
     * 分割固定格式的字符串
     */
    public static String[] split(String str, String separator) {
        return StringUtils.splitByWholeSeparator(str, separator);
    }

}
