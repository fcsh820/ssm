package com.demo.ssm.util;

import java.util.List;

/**
 * Created by Administrator on 2015/12/7.
 * 校验工具类
 */
public class ChkTools {

    public static boolean isNull(Integer num) {
        if (num == null || num == 0) {
            return true;
        } else {
            return false;
        }
    }//#isNull

    public static boolean isNull(Object str) {
        if (str == null || "".equals(str.toString())) {
            return true;
        } else {
            return false;
        }
    }//#isNull

    public static boolean isNull(Object[] objs) {
        if (objs == null || objs.length == 0) {
            return true;
        } else {
            return false;
        }//#isNull
    }

    public static boolean isNull(List<?> list) {
        if (list == null || list.size() == 0) {
            return true;
        } else {
            return false;
        }//#isNull
    }

    public static boolean isNotNull(List<?> list) {
        return !isNull(list);
    }// #isNotNull

    public static boolean isNotNull(Object[] objs) {
        return !isNull(objs);
    }// #isNotNull

    public static boolean isNotNull(Object str) {
        return !isNull(str);
    }// #isNotNull

    public static boolean isNotNull(Integer num) {
        return !isNull(num);
    }

    /**
     * 验证是否为邮箱
     *
     * @param acc
     * @return
     */
    public static boolean isMail(String acc) {
        return true;
    }

    public static boolean isInteger(String arg) {
        if (isNull(arg)) {
            return false;
        }
        return arg.matches("-{0,1}\\d+");
    }

    public static Integer getInteger(String arg) {
        if (isInteger(arg)) {
            return new Integer(arg);
        } else {
            return 0;
        }
    }

    /**
     * 判断时间格式00:00:00
     * @param time
     * @return
     */
    public static boolean isTime(String time) {
        String format = "";
        if (isNull(time)) {
            return false;
        }
        return time.matches(format);
    }

    /**
     * 判断是否为基本数据类型
     * @param paramType
     * @return
     */
    public static boolean isBasicType(Class<?> paramType) {
        Class<?>[] basic = { Boolean.class, Character.class, Byte.class,
                Short.class, Integer.class, Long.class, Float.class,
                Double.class };
        for (Class<?> b : basic) {
            if (b.equals(paramType)) {
                return true;
            }
        }
        if (paramType.isPrimitive()) {
            return true;
        }
        return false;
    }
}