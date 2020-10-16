package com.mall.common.vo;

public class MallResult<T> {

    private boolean success;

    private String msg;

    private String code;

    private T data;

    public MallResult(boolean success, String msg, String code, T data) {
        this.success = success;
        this.msg = msg;
        this.code = code;
        this.data = data;
    }

    public static <T> MallResult<T> success(T data) {
        return new MallResult<T>(true,null,null,data);
    }

    public static <T> MallResult<T> success() {
        return new MallResult<T>(true,null,"200",null);
    }


    public static <T> MallResult<T> fail(String code, String msg) {
        return new MallResult<T>(false,msg,code,null);
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
