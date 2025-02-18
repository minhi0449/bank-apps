package com.applus_bank.dto;

import com.applus_bank.repository.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
    날짜 : 2025.02.18 (화)
    이름 : 김민희
    내용 : 로그인 DTO - 로그인할 때, 필요한 데이터를 담는 DTO
 */

@Data // getter, setter, toString(), equal(), hashCode() 자동 생성
@NoArgsConstructor // 기본 생성자 생성 (매개변수 없는 생성자)
@AllArgsConstructor // 모든 필드를 포함하는 생성자 생성
@Builder // 빌더 패턴 적용 (햄버거 가게에서 원하는 재료만 선택해서 햄버거를 조립하는 방식)
public class SignInDTO {
    private String username;
    private String password;

    // toUser 메서드 → SignInDTO 를 User 객체로 변환하는 기능
    public User toUser(){
        return User.builder() // User 객체를 빌더 패턴으로 생성
                .username(this.username) // 현재 DTO 의 username 값을 User 객체로 넘김
                .password(this.password)
                .build(); // 최종적으로 User 객체를 만들어서 반환
    }

}
