package com.baole.common.error;

import com.baole.common.constants.ErrorConstant;
import lombok.Getter;

/**
 * 错误编码
 */
@Getter
public enum ErrorCode {

    // 通用异常（业务异常） -------------------------------------------------------------

    /**
     * 入参异常（业务异常）
     */
    PARAM_ERROR(ErrorConstant.ErrorCodePrefix.COMMON_MODEL_PREFIX, "0001", "入参异常"),

    /**
     * 对象不能为空（业务异常）
     */
    OBJECT_CANNOT_NULL(ErrorConstant.ErrorCodePrefix.COMMON_MODEL_PREFIX, "0002", "对象不能为空"),

    /**
     * 字符串不能为空白（业务异常）
     */
    STRING_CANNOT_BLANK(ErrorConstant.ErrorCodePrefix.COMMON_MODEL_PREFIX, "0003", "字符串不能为空白"),

    /**
     * 入参容量超出限制（业务异常）
     */
    PARAM_CAPACITY_OUT_OF_LIMIT(ErrorConstant.ErrorCodePrefix.COMMON_MODEL_PREFIX, "0004", "入参容量超出限制"),

    /**
     * es 索引名不能为空白（业务异常）
     */
    ES_INDEX_NAME_CANNOT_BLANK(ErrorConstant.ErrorCodePrefix.COMMON_MODEL_PREFIX, "0005", "es 索引名不能为空白"),


    // （仓储层异常） -------------------------------------------------------------

    /**
     * 对象不能为空（仓储层异常）
     */
    OBJECT_CANNOT_NULL_REPOSITORY(ErrorConstant.ErrorCodePrefix.REPOSITORY_MODEL_PREFIX, "0001", "对象不能为空"),

    /**
     * 字符串不能为空白（仓储层异常）
     */
    STRING_CANNOT_BLANK_REPOSITORY(ErrorConstant.ErrorCodePrefix.REPOSITORY_MODEL_PREFIX, "0002", "字符串不能为空白"),

    /**
     * map 不能为空（仓储层异常）
     */
    MAP_CANNOT_EMPTY_REPOSITORY(ErrorConstant.ErrorCodePrefix.REPOSITORY_MODEL_PREFIX, "0003", "map 不能为空"),

    /**
     * 分页大小超出限制（仓储层异常）
     */
    PAGE_SIZE_OUT_OF_LIMIT_REPOSITORY(ErrorConstant.ErrorCodePrefix.REPOSITORY_MODEL_PREFIX, "0004", "分页大小超出限制"),


    // （外部调用异常） -------------------------------------------------------------

    /**
     * 根据 es 索引查询扩展字段映射失败（外部调用异常）
     */
    GOODS_EXT_MAPPING_GET_BY_ES_INDEX_ERROR_INTEGRATION(ErrorConstant.ErrorCodePrefix.INTEGRATION_MODEL_PREFIX, "0001", "根据 es 索引查询扩展字段映射失败"),

    /**
     * 根据商品 ids 查询商品扩展信息失败（外部调用异常）
     */
    GOODS_EXT_INFO_GET_BY_GOODS_IDS_ERROR_INTEGRATION(ErrorConstant.ErrorCodePrefix.INTEGRATION_MODEL_PREFIX, "0002", "根据商品 ids 查询商品扩展信息失败"),

    ;

    /**
     * 构造方法
     *
     * @param model   子模块
     * @param code    错误码
     * @param message 描述
     */
    ErrorCode(String model, String code, String message) {

        this.code = ErrorConstant.ErrorCodePrefix.BIZ_PREFIX + ErrorConstant.ErrorCodePrefix.SUB_SYS_PREFIX + model + code;
        this.message = message;
    }

    /**
     * 编码
     */
    private final String code;

    /**
     * 描述信息
     */
    private final String message;

}
