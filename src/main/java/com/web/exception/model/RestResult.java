package com.web.exception.model;

// 目前測試結果，如果此處繼承RuntimeException
// String message會被exception.getMessage()給覆蓋掉
// 也就是在運行途中的所有程序，全部都會被message記錄
// 所以，其他有調用到此處的message，都會顯示執行的全部程序（無論成功或異常）
// 若要繼續深度學習：請查閱【自定義異常】
public class RestResult {
    private int code;
    private String message;
    private Object data;

    public RestResult() {
    }

    public int getCode() {
        return code;
    }

    public RestResult setCode(ResultCode code) {
        this.code = code.getCode();
        return this;
    }

    public String getMessage() {
        return message;
    }

    public RestResult setMessage(String message) {
        this.message = message;
        return this;
    }

    public Object getData() {
        return data;
    }

    public RestResult setData(Object data) {
        this.data = data;
        return this;
    }
}
