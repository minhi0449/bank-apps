package com.applus_bank.handler.exception;

import org.springframework.http.HttpStatus;

/*
    날짜 : 2025.02.14 (금)
    이름 : 김민희
    내용 : 인증되지 않은 접근 시 발생하는 예외 클래스
 */

// 인증되지 않은 사용자에 대한 예외 처리
public class UnAuthorizedException extends RuntimeException{

    private HttpStatus status;

    public UnAuthorizedException (String message, HttpStatus status){
        super(message);
        this.status = status;
    }

}
