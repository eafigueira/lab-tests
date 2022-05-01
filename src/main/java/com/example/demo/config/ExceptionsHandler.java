package com.example.demo.config;

import com.example.demo.exceptions.BusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class ExceptionsHandler {

    @ExceptionHandler(value = {BusinessException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public DataResult businessExceptionHandler(BusinessException ex, WebRequest request) {
        return DataResult.builder().success(false).error(ex.getMessage()).build();
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public DataResult argumentNotValidExceptionHandler(MethodArgumentNotValidException ex, WebRequest request) {
        String messageError = String.format("Field %s - %s", ex.getBindingResult().getFieldErrors().get(0).getField(), ex.getBindingResult().getFieldErrors().get(0).getDefaultMessage());
        return DataResult.builder().success(false).error(messageError).build();
    }

}
