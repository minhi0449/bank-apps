package com.applus_bank.handler.exception;

import org.springframework.http.HttpStatus;

/**
 * 날짜 : 2025.02.14 (금)
 * 이름 : 김민희
 * 내용 : 데이터 전송 중 발생하는 예외를 처리하는 클래스
 */

/*
    날짜 : 2025.02.14 (금)
    이름 : 김민희
    내용 : 데이터 전송 중 발생하는 예외를 처리하는 클래스
 */

// 사용자 정의 예외 클래스 만들기
public class DataDeliveryException extends RuntimeException{
    private HttpStatus status;

    // 예외 발생했을 때, → HTTP 상태코드를 알려준다.
    // 어떤 예외 발생했는지, 상태 메세지를 알려준다.
    // 메세지 (어떤 예외 발생)

    /**
     *
     * @param message
     * @param status
     */
    public DataDeliveryException (String message, HttpStatus status){
        super(message);
        this.status = status;
    }
}
