package com.baole.search.dto.es.response;

public class SoaResponse<T, ErrT> implements java.io.Serializable {
    private static final java.lang.String RETURN_SUCCESS = "000000";
    private static final long serialVersionUID = -2614048007780337590L;
    private java.lang.String returnCode;
    private java.lang.String returnMsg;
    private java.lang.String logBizData;
    private java.lang.Boolean processResult;
    private java.lang.Boolean bizProcessResult;
    private T responseVo;
    private ErrT errT;
    private java.lang.String monitorTrackId;
    private java.lang.String timestamp;
    private java.lang.String globalTicket;
}
