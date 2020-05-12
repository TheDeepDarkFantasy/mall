package com.hyz.mall.common.api;




public enum  ResultCode implements IErrorCode {

    /**
     * 枚举了一些常用API操作码
     */
    SUCCESS(200, "操作成功"),
    FAILED(500, "操作失败"),
    VALIDATE_FAILED(404, "参数检验失败"),
    UNAUTHORIZED(401, "暂未登录或token已经过期"),
    FORBIDDEN(403, "没有相关权限");


    /**
     * 操作码
     */
    private long code;
    /**
     * 操作信息
     */
    private String message;



    private ResultCode(long code,String message){





        this.code=code;
        this.message=message;
    }





    @Override
    public long getCode() {
        return 0;
    }

    @Override
    public String getMessage() {
        return null;
    }
}
