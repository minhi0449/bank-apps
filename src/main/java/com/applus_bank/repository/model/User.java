package com.applus_bank.repository.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

/*
    날짜 : 2025.02.17 (일)
    이름 : 김민희
    내용 : 사용자(User) 정보를 저장하는 모델 클래스
 */

@Data
@Builder // 쉽게 객체를 만들 수 있도록 도와줘
@NoArgsConstructor // 아무 값 없이도 객체를 만들 수 있게 함 (new User();)
@AllArgsConstructor // 모든 값을 받는 생성자를 자동으로 만들어 줌
// Lonbok (룸북) : 자동으로 코드 줄여주는 도구
public class User {

    private Integer id; // 사용자 고유 ID (데이터베이스 PK)
    private String username; // 사용자의 로그인 아이디
    private String password; // 사용자 비밀번호 (암호화 필요)
    private String fullname; // 사용자 실제 이름
    private Timestamp createAt; // 계정 생성일 (데이터베이스에서 자동 저장)
    private String uploadFileName; // 업로드된 프로필 이미지 파일 이름

    /**
     * 사용자의 프로필 이미지를 반환하는 메서드
     * - 사용자가 이미지를 업로드했으면 해당 파일 경로를 반환
     * - 업로드 하지 않았다면 기본 이미지 반환
     * @return
     */
    public String setUpUserImage(){
        // uploadFileName == null ? "기본 이미지" : "사용자 이미지"
        return uploadFileName == null ?
                "https://picsum.photos/id/237/200/300"
                : "images/uploads/" + uploadFileName; // 사용자가 업로드한 이미지 경로
    }

}
