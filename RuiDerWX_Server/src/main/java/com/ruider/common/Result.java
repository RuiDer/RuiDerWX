package com.ruider.common;

/**
 * Created by mahede on 2018/11/28.
 */
public class Result {

    public static final int ERROR_CODE = 100;

    public static final int ALREADY_SAVED = 110;

    public static final int FAIL_CODE = 120;

    public static final int SUCCESS_CODE = 140;

    public static final int EXCEPTION_CODE = 150;

    public static final int OVER_NUMBER = 160;

    public static final int OVER_TIME = 170;

    int code;

    String message;

    Object data;

    boolean isSuccess;

    public Result(){ this.code = SUCCESS_CODE;}

    public Result(int code, String message, Object data, boolean isSuccess) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.isSuccess = isSuccess;
    }

    public Result(boolean isSuccess, Object data, String message) {
        this.message = message;
        this.data = data;
        this.isSuccess = isSuccess;
    }

    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                ", isSuccess=" + isSuccess +
                '}';
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public boolean getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }
}
