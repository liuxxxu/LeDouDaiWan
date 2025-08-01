package com.liuxxx.ledoudaiwan.common.handler;

import com.liuxxx.ledoudaiwan.common.exception.BusinessException;
import com.liuxxx.ledoudaiwan.common.result.Result;
import com.liuxxx.ledoudaiwan.common.result.StatusEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 处理业务异常
     */
    @ExceptionHandler(BusinessException.class)
    public Result<Void> handleBusinessException(BusinessException exception) {
        log.error(exception.getMessage(), exception);
        return Result.error(exception.getStatus());
    }

    /**
     * 处理资源不存在异常
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    public Result<Void> handleNoHandlerFoundException(NoHandlerFoundException exception) {
        log.error(StatusEnum.NOT_FOUND.message, exception);
        return Result.error(StatusEnum.NOT_FOUND);
    }

    /**
     * 处理请求方法不支持异常
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Result<Void> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException exception) {
        log.error(StatusEnum.METHOD_NOT_ALLOWED.message, exception);
        return Result.error(StatusEnum.METHOD_NOT_ALLOWED);
    }

    /**
     * 处理参数校验异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result<Void> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        try {
            List<ObjectError> errors = ex.getBindingResult().getAllErrors();
            String message = errors.stream().map(ObjectError::getDefaultMessage).collect(Collectors.joining(","));
            log.error(StatusEnum.PARAM_INVALID.message + ": {}", message);
            return Result.error(StatusEnum.PARAM_INVALID, message);
        } catch (Exception e) {
            return Result.error(StatusEnum.SERVICE_ERROR);
        }
    }

    /**
     * 处理未捕获的异常
     */
    @ExceptionHandler(Throwable.class)
    public Result<Void> handleException(Throwable throwable) {
        log.error(throwable.getMessage(), throwable);
        return Result.error(StatusEnum.SERVICE_ERROR);
    }

}