package com.liuxxx.ledoudaiwan.common.result;

import lombok.Data;

import java.io.Serializable;

@Data
public class Result<T> implements Serializable {
    private static final long serialVersionUID = -1133637474601003587L;

    /**
     * 接口响应状态码
     */
    private Integer code;

    /**
     * 接口响应信息
     */
    private String msg;

    /**
     * 接口响应的数据
     */
    private T data;

    /**
     * 封装成功响应的方法
     */
    public static <T> Result<T> success(T data) {
        Result<T> response = new Result<>();
        response.setData(data);
        response.setCode(StatusEnum.SUCCESS.code);
        response.setMsg(StatusEnum.SUCCESS.message);
        return response;
    }

    /**
     * 封装成功响应的方法
     */
    public static <T> Result<T> success() {
        return success(null);
    }

    /**
     * 封装error的响应
     */
    public static <T> Result<T> error(StatusEnum statusEnum) {
        return error(statusEnum, statusEnum.message);
    }

    /**
     * 封装error的响应 可自定义错误信息
     */
    public static <T> Result<T> error(StatusEnum statusEnum, String errorMsg) {
        Result<T> response = new Result<>();
        response.setCode(statusEnum.code);
        response.setMsg(errorMsg);
        return response;
    }
}
