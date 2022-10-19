package com.forever.user.web.exception;

import com.forever.user.web.common.ResponseResult;
import com.sun.mail.smtp.SMTPSendFailedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.MailSendException;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.stream.Collectors;

/**
 * Description: 全局异常处理
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 针对不同的异常进行处理
     * 此处是对方法参数校验异常进行处理
     * @param e
     * @return
     */
    @ExceptionHandler({BindException.class, ConstraintViolationException.class, MethodArgumentNotValidException.class})
    public ResponseResult<String> handlerIllegalArgumentException(Exception e) {
        String message;
        if (e instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException ex = (MethodArgumentNotValidException) e;
            message = ex.getBindingResult().getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.joining(","));
        } else if (e instanceof BindException) {
            BindException ex = (BindException) e;
            message = ex.getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.joining(","));
        } else {
            ConstraintViolationException ex = (ConstraintViolationException) e;
            message = ex.getConstraintViolations().stream().map(ConstraintViolation::getMessage).collect(Collectors.joining(","));
        }
        return ResponseResult.ERROR(message);
    }

    /**
     * 未指定处理的异常统一由此处理
     * @param ex
     * @return
     */
    @ExceptionHandler(value = {RuntimeException.class})
    public ResponseResult<String> handlerException(RuntimeException ex) {
        log.error("inner error：", ex);
        return ResponseResult.ERROR("服务内部异常");
    }

    /**
     * 发送邮件的配置缺失异常
     * @param ex
     * @return
     */
    @ExceptionHandler(value = {MailSendException.class})
    public ResponseResult<String> handlerSMTPException(MailSendException ex) {
        log.error("inner error:", ex);
        return ResponseResult.ERROR("邮箱认证未配置，请先配置认证邮箱");
    }
}
