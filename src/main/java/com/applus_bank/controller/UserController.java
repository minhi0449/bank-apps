package com.applus_bank.controller;

import com.applus_bank.dto.SignUpDTO;
import com.applus_bank.handler.exception.DataDeliveryException;
import com.applus_bank.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/*
    날짜 : 2025.02.17 (일\)
    이름 : 김민희
    내용 :
 */

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
@Log4j2
public class UserController {

    // @Autowired
    private final UserService userService;
    private final HttpSession session;

    // 회원가입
    @GetMapping("sign-up")
    public String signUpPage(){
        return "/user/signUp";
    }

    // 회원가입
    @PostMapping("/sign-up")
    public String signProd(SignUpDTO dto){
        log.info(" ^^! signProd () - 회원가입 하러 왔니?? ");
        // 여기서 회원가입 해야 해서 인증검사 필요 없씀
        // 2. 유효성 검사
        if(dto.getUsername() == null || dto.getUsername().isEmpty()){
            throw new DataDeliveryException("❗username 사용자 이름을 입력하세요", HttpStatus.BAD_REQUEST);
        }

        if(dto.getPassword() == null || dto.getPassword().isEmpty()){
            throw new DataDeliveryException("🤫 password 비밀번호를 입력하세요.", HttpStatus.BAD_REQUEST);
        }

        if(dto.getFullname() == null || dto.getFullname().isEmpty()){
            throw new DataDeliveryException("🔎 fullname 전체 이름(실명)을 입력하세요.", HttpStatus.BAD_REQUEST);
        }
        userService.createUser(dto);
        // TODO : /account/list 경로로 변경해야 함
        return "redirect:/user/sign-in";
    }


}
