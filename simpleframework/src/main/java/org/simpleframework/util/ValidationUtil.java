package org.simpleframework.util;

import java.util.Collection;
import java.util.Map;

public class ValidationUtil {
    /**
     * 判断String是否为null或者""
     * @param obj String
     * @return 是否为空
     */
    public static boolean isEmpty(String obj){
        return (null==obj || "".equals(obj));
    }

    /**
     * 判断Array是否为null或者size为0
     * @param obj
     * @return
     */
    public static boolean isEmpty(Object[] obj){
        return null==obj || 0==obj.length;
    }

    /**
     * 判断Collection是否为null或者为空
     * @param obj Collection
     * @return 是否为空
     */
    public static boolean isEmpty(Collection<?> obj){
        return null==obj || obj.isEmpty();
    }

    /**
     * Map是否为null或者size为0
     * @param obj Map
     * @return 是否为空
     */
    public static boolean isEmpty(Map<?, ?> obj){
        return null==obj || obj.isEmpty();
    }
}
