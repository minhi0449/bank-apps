package com.applus_bank.dto;

import com.applus_bank.repository.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;



@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SignUpDTO {
    private String username; // 사용자 이름
    private String password; // 비밀번호
    private String fullname; // 전체 이름 (실명)

    // TODO : 추후 진행 예정
//    private MultipartFile customFile; // name 속성과 일치 시켜야 함
//    private String originFileName;
//    private String uploadFileName;
//    private String eMail;

    // DTO --> User 객체로 변환하는 메서드
    public User toUser(){
        return User.builder()
                .username(this.username)
                .password(this.password)
                .fullname(this.fullname) // 현재 DTO의 fullname을 User 객체로 전달
                .build(); // User 객체 생성 완료
    }

}
