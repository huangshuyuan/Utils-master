//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.hsy.utils.utilslibrary.utils;

public class EmptyUtils {
    private static final int EMPTY_SIZE = 0;

    public EmptyUtils() {
    }

    public static boolean isEmpty(Object[] obj) {
        return null == obj || 0 == obj.length;
    }

    public static boolean isNotEmpty(Object[] obj) {
        return !isEmpty(obj);
    }

    public static boolean isEmpty(Object obj) {
        return null == obj;
    }

    public static boolean isNotEmpty(Object obj) {
        return !isEmpty(obj);
    }
}
