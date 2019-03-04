package com.web.exception.model;

public enum ResultCode {
    //public static final SUCCESS = <enum> ResultCode.SUCCESS
    SUCCESS(200),
    FAIL(400),
    UNAUTHORIZED(401),
    NOT_FOUND(404),
    INTERNAL_SERVER_ERROR(500);

    private int code;

    //如果打算自定义自己的方法，那么必须在enum实例序列的最后添加一个分号。而且 Java 要求必须先定义 enum 实例。
    //https://blog.csdn.net/qq_27093465/article/details/52180865
    ResultCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
