package com.applus_bank.repository.model;

import com.applus_bank.handler.exception.DataDeliveryException;
import org.springframework.http.HttpStatus;

import java.security.Timestamp;

/*
    날짜 : 2025.02.20 (목)
    이름 : 김민희
    내용 : Account 모델 설계
 */
// Account Entity 를 설계 중입니다. -> MySQL 에서는 엔티티라고 잘 말하지 않음
// Entity 로 사용하는 클래스는 로직을 포함할 수 있다.
public class Account {
    private Integer id;
    private String number;
    private String password;
    private Long balance;
    private Integer userId;
    private Timestamp createdAt;

    // 출금 기능
    public void withdraw(Long amount){
        this.balance -= amount;
    }

    // 입금 기능
    public void deposit(Long amount){
        this.balance += amount;
    }

    // 패스워드 체크 기능
    public boolean checkPassword(String password){
        boolean isOk = true;
        if(this.password.equals(password) == false){
            // 사용자한테 비밀번호 틀렸어요.
            isOk = false;
            throw new DataDeliveryException("계좌 비밀번호 틀렸어요.", HttpStatus.BAD_REQUEST);
        }
        return isOk;
    }

    // 잔액 여부 확인 가능
    public void checkBalance(Long amount){
        if(this.balance < amount){
            // 내 잔액보다 더 많은 금액을 출금하려고 하면 global 거기로 exception 으로 던져버려서 리턴이 필요 없음.
            throw new DataDeliveryException("잔액이 부족합니다.", HttpStatus.BAD_REQUEST);
        }
    }
}
