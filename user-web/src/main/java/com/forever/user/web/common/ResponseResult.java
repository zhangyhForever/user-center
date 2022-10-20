package com.forever.user.web.common;

import java.io.Serializable;

/**
 * Description: web接口统一返回
 */
public class ResponseResult<T> implements Serializable {

    private Integer code;

    private boolean success;

    private String message;

    private T data;

    private static final Integer SUCCESS = 0;

    private static final Integer ERROR = 1;

    public ResponseResult(Integer code, Boolean success, String message, T data) {
        this.code = code;
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public static <T> ResponseResult<T> OK() {
        return OK(SUCCESS, null, null);
    }

    public static <T> ResponseResult<T> OK(String message) {
        return OK(SUCCESS, message, null);
    }

    public static <T> ResponseResult<T> OK(T data) {
        return OK(SUCCESS, null, data);
    }

    private static <T> ResponseResult<T> OK(Integer code, String message, T t) {
        return new ResponseResult(code, true, message, t);
    }

    public static <T> ResponseResult<T> ERROR(String message) {
        return ERROR(ERROR, message);
    }

    public static <T> ResponseResult<T> ERROR(Integer code, String message) {
        return new ResponseResult(code, false, message, null);
    }

    public boolean isSuccess() {
        return success;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
