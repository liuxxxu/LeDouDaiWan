package com.liuxxx.ledoudaiwan.common.result;

public enum StatusEnum {
    SUCCESS(200, "请求成功"),
    PARAM_INVALID(400, "参数错误"),
    UNAUTHORIZED(401, "认证失败"),
    FORBIDDEN(403, "权限不足"),
    NOT_FOUND(404, "资源不存在"),
    METHOD_NOT_ALLOWED(405, "请求方法不支持"),
    SERVICE_ERROR(500, "服务器错误，请稍后再试"),
    ;

    public final Integer code;

    public final String message;

    StatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}
