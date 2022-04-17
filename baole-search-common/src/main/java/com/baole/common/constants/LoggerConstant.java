package com.baole.common.constants;

/**
 * 日志字典
 */
public interface LoggerConstant {

    /**
     * topic
     */
    interface LoggerTopic {

        /**
         * core 层和部分 server 层处理逻辑日志
         */
        String SERVICE_LOGGER = "serviceLogger";

        /**
         * server 层 api 处理逻辑日志
         */
        String API_LOGGER = "apiLogger";

        /**
         * server 层 ability 处理逻辑日志
         */
        String ABILITY_LOGGER = "abilityLogger";
    }

    interface Es{
        /**es总耗时，包含网络耗时*/
        String ES_TIME="esTime[es总耗时（包含网络耗时）]";
        /**es本身耗时*/
        String SEARCH_TIME="esSearchTime[es本身耗时]";
    }
}
