package com.assignment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalSpringExceptionHandler {


    @ExceptionHandler(IllegalArgumentException.class)
    public @ResponseBody
    ExceptionResponseJson illegalArguementException
            (HttpServletRequest request, Exception ex){
        ExceptionResponseJson response = new ExceptionResponseJson();
        response.setUrl(request.getRequestURL().toString());
        response.setMessage(ex.getMessage());
        response.setStatusCode(HttpStatus.BAD_REQUEST.value());
        return response;
    }

    @ExceptionHandler(TokenException.class)
    public @ResponseBody
    ExceptionResponseJson tokenException
            (HttpServletRequest request, Exception ex){
        ExceptionResponseJson response = new ExceptionResponseJson();
        response.setUrl(request.getRequestURL().toString());
        response.setMessage(ex.getMessage());
        response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        return response;
    }


    @ExceptionHandler(Exception.class)
    public @ResponseBody
    ExceptionResponseJson generalException
            (HttpServletRequest request, Exception ex){
        ExceptionResponseJson response = new ExceptionResponseJson();
        response.setUrl(request.getRequestURL().toString());
        response.setMessage(ex.getMessage());
        response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        return response;
    }

}
