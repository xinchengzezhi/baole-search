package com.baole.common.error;


/**
 * 校验异常
 */
public class VerifyException extends RuntimeException {

    private static final long serialVersionUID = -1498886496477006953L;
    /**异常编码*/
    private String errorCode;
    /**异常信息*/
    private String errorMsg;
    /**
     * 构造方法
     *
     * @param errorCode 错误编码
     */
    public VerifyException(ErrorCode code) {
        this.errorCode = code.getCode();
        this.errorMsg = String.format(code.getMessage());
    }

    /**
     * 构造方法
     *
     * @param errorCode 错误编码
     */
    public VerifyException(ErrorCode code, String remark) {
        this.errorCode = code.getCode();
        this.errorMsg = String.format(code.getMessage(), remark);
    }
}
