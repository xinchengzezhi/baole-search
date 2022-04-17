package com.baole.common.constants;

/**
 * 错误字典
 */
public interface ErrorConstant {

    /**
     * 错误编码前缀
     */
    interface ErrorCodePrefix {

        // 公共前缀 --------------------------------------------

        /**
         * 业务前缀
         */
        String BIZ_PREFIX = "90003";

        /**
         * 子系统前缀
         */
        String SUB_SYS_PREFIX = "108";


        // 模块前缀 --------------------------------------------

        /**
         * 通用模块前缀
         */
        String COMMON_MODEL_PREFIX = "01";

        /**
         * 仓储层模块前缀
         */
        String REPOSITORY_MODEL_PREFIX = "02";

        /**
         * 外部调用层模块前缀
         */
        String INTEGRATION_MODEL_PREFIX = "03";

    }
}
