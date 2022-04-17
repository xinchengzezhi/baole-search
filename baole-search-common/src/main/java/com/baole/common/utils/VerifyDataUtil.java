package com.baole.common.utils;

import com.baole.common.error.ErrorCode;
import com.baole.common.error.VerifyException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.lang.NonNull;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;

/**
 * 校验数据
 */
public class VerifyDataUtil {

    /**
     * 校验对象不为空
     *
     * @param errorCode 错误编码
     * @param objects   对象组
     */
    public static void checkNotNull(@NonNull ErrorCode errorCode, Object... objects) {

        checkNotNull(null, errorCode, objects);
    }

    /**
     * 校验对象不为空
     *
     * @param remark    错误信息补充
     * @param errorCode 错误编码
     * @param objects   对象组
     */
    public static void checkNotNull(String remark, @NonNull ErrorCode errorCode, Object... objects) {

        if (objects == null) {

            throw new VerifyException(errorCode, remark);
        }

        for (Object object : objects) {

            if (object == null) {

                throw new VerifyException(errorCode, remark);
            }
        }
    }

    /**
     * 校验字符串不为空白
     *
     * @param errorCode 错误编码
     * @param strings   字符串组
     */
    public static void checkNotBlank(@NonNull ErrorCode errorCode, String... strings) {

        checkNotBlank(null, errorCode, strings);
    }

    /**
     * 校验字符串不为空白
     *
     * @param remark    错误信息补充
     * @param errorCode 错误编码
     * @param strings   字符串组
     */
    public static void checkNotBlank(String remark, @NonNull ErrorCode errorCode, String... strings) {

        if (strings == null) {

            throw new VerifyException(errorCode, remark);
        }

        for (String string : strings) {

            if (StringUtils.isBlank(string)) {

                throw new VerifyException(errorCode, remark);
            }
        }
    }

    /**
     * 校验 list 不为空
     *
     * @param errorCode 错误编码
     * @param list      List
     */
    public static void checkListNotEmpty(@NonNull ErrorCode errorCode, List<?> list) {

        checkListNotEmpty(null, errorCode, list);
    }

    /**
     * 校验 list 不为空
     *
     * @param remark    错误信息补充
     * @param errorCode 错误编码
     * @param list      List
     */
    public static void checkListNotEmpty(String remark, @NonNull ErrorCode errorCode, List<?> list) {

        if (CollectionUtils.isEmpty(list)) {

            throw new VerifyException(errorCode, remark);
        }
    }

    /**
     * 校验 list 不为空 & list 容量不超出限制
     *
     * @param errorCode 错误编码
     * @param list      List
     * @param capacity  容量
     */
    public static void checkListCapacityNotExceedLimit(@NonNull ErrorCode errorCode, List<?> list, int capacity) {

        checkListCapacityNotExceedLimit(null, errorCode, list, capacity);
    }

    /**
     * 校验 list 不为空 & list 容量不超出限制
     *
     * @param remark    错误信息补充
     * @param errorCode 错误编码
     * @param list      List
     * @param capacity  容量
     */
    public static void checkListCapacityNotExceedLimit(String remark, @NonNull ErrorCode errorCode, List<?> list, int capacity) {

        if (CollectionUtils.isEmpty(list)) {

            throw new VerifyException(errorCode, remark);
        }

        if (list.size() > capacity) {

            throw new VerifyException(errorCode, remark);
        }
    }

    /**
     * 校验 map 不为空
     *
     * @param errorCode 错误编码
     * @param map       map
     */
    public static void checkMapNotEmpty(@NonNull ErrorCode errorCode, Map<?, ?> map) {

        checkMapNotEmpty(null, errorCode, map);
    }

    /**
     * 校验 map 不为空
     *
     * @param remark    错误信息补充
     * @param errorCode 错误编码
     * @param map       map
     */
    public static void checkMapNotEmpty(String remark, @NonNull ErrorCode errorCode, Map<?, ?> map) {

        if (CollectionUtils.isEmpty(map)) {

            throw new VerifyException(errorCode, remark);
        }
    }

    /**
     * 校验 number 不超出限制
     *
     * @param errorCode 错误编码
     * @param number    数字
     * @param min       最小限制
     * @param max       最大限制
     */
    public static void checkNumberNotExceedLimit(@NonNull ErrorCode errorCode, int number, int min, int max) {

        checkNumberNotExceedLimit(null, errorCode, number, min, max);
    }

    /**
     * 校验 number 不超出限制
     *
     * @param remark    错误信息补充
     * @param errorCode 错误编码
     * @param number    数字
     * @param min       最小限制
     * @param max       最大限制
     */
    public static void checkNumberNotExceedLimit(String remark, @NonNull ErrorCode errorCode, int number, int min, int max) {

        if (number < min || number > max) {
            throw new VerifyException(errorCode, remark);
        }
    }

    /**
     * 校验 number 不超出限制
     *
     * @param errorCode 错误编码
     * @param number    数字
     * @param min       最小限制
     */
    public static void checkNumberNotExceedMinLimit(@NonNull ErrorCode errorCode, int number, int min) {

        checkNumberNotExceedMinLimit(null, errorCode, number, min);
    }

    /**
     * 校验 number 不超出限制
     *
     * @param remark    错误信息补充
     * @param errorCode 错误编码
     * @param number    数字
     * @param min       最小限制
     */
    public static void checkNumberNotExceedMinLimit(String remark, @NonNull ErrorCode errorCode, int number, int min) {

        if (number < min) {
            throw new VerifyException(errorCode, remark);
        }
    }

    /**
     * 校验 number 不超出限制
     *
     * @param errorCode 错误编码
     * @param number    数字
     * @param max       最大限制
     */
    public static void checkNumberNotExceedMaxLimit(@NonNull ErrorCode errorCode, int number, int max) {

        checkNumberNotExceedMaxLimit(null, errorCode, number, max);
    }

    /**
     * 校验 number 不超出限制
     *
     * @param remark    错误信息补充
     * @param errorCode 错误编码
     * @param number    数字
     * @param max       最大限制
     */
    public static void checkNumberNotExceedMaxLimit(String remark, @NonNull ErrorCode errorCode, int number, int max) {

        if (number > max) {
            throw new VerifyException(errorCode, remark);
        }
    }

    /**
     * 校验 flag true
     *
     * @param errorCode 错误编码
     * @param flag      boolean
     */
    public static void checkBooleanTrue(@NonNull ErrorCode errorCode, boolean flag) {

        checkBooleanTrue(null, errorCode, flag);
    }

    /**
     * 校验 flag true
     *
     * @param remark    错误信息补充
     * @param errorCode 错误编码
     * @param flag      boolean
     */
    public static void checkBooleanTrue(String remark, @NonNull ErrorCode errorCode, boolean flag) {

        if (!flag) {
            throw new VerifyException(errorCode, remark);
        }
    }
}
