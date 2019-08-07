package com.zzz.orderpool.params;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @author zzz
 * @date 2019/8/6 15:43
 **/
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response<T> {

    private static final int SUCCESS_CODE = 200;

    private static final String SUCCESS_MESSAGE = "请求成功";

    /**
     * 返回码
     */
    private int code;

    /**
     * 返回信息
     */
    private String message;

    /**
     * 返回数据
     */
    private T data;

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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static <T> Response<T> success() {
        return build(SUCCESS_CODE, SUCCESS_MESSAGE, null);
    }

    public static <T> Response<T> success(T data) {
        return build(SUCCESS_CODE, SUCCESS_MESSAGE, data);
    }

    public static <T> Response<T> success(String message, T data) {
        return build(SUCCESS_CODE, message, data);
    }

    public static <T> Response<T> failed(int code, String message, T data) {
        return build(code, message, data);
    }

    private static <T> Response<T> build(int code, String message, T data) {
        Response<T> resp = new Response<>();
        resp.code = code;
        resp.message = message;
        resp.data = data;
        return resp;
    }

}
