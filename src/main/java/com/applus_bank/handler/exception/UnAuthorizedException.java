package com.applus_bank.handler.exception;

import org.springframework.http.HttpStatus;

// 인증되지 않은 사용자에 대한 예외 처리
public class UnAuthorizedException extends RuntimeException{

    private HttpStatus status;

    public UnAuthorizedException (String message, HttpStatus status){
        super(message);
        this.status = status;
    }

}
