package com.liuxxx.ledoudaiwan.common.exception;

import com.liuxxx.ledoudaiwan.common.result.StatusEnum;
import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = -3303518302920463234L;

    private final StatusEnum status;

    public BusinessException(StatusEnum status, String message) {
        super(message);
        this.status = status;
    }

    public BusinessException(StatusEnum status) {
        this(status, status.message);
    }
}
