package com.applus_bank.handler.exception;


import lombok.Getter;
import org.springframework.http.HttpStatus;

// 리다이렉트 예외
// 리다이렉트란?
@Getter
public class RedirectException extends RuntimeException{

    private HttpStatus status;

    // 예외 발생 했을 때, --> HTTP 상태코드를 알려준다.
    // 메세지 (어떤 예외 발생)
    public RedirectException (String message, HttpStatus status){
        super(message);
        this.status = status;
    }

}
