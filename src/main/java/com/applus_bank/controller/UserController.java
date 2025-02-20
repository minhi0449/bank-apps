package com.applus_bank.controller;

import com.applus_bank.dto.SignInDTO;
import com.applus_bank.dto.SignUpDTO;
import com.applus_bank.handler.exception.DataDeliveryException;
import com.applus_bank.repository.model.User;
import com.applus_bank.service.UserService;
import com.applus_bank.utils.Define;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
        log.info("⏹️ GetMapping() - 회원가입 페이지 : signUpPage()");
        return "/user/signUp";
    }

    // 회원가입
    @PostMapping("/sign-up")
    public String signProc(SignUpDTO dto){
        log.info("ℹ️ PostMapping () - 회원가입 하러 왔니?? ^^! signProc ()");
        // 여기서 회원가입 해야 해서 인증검사 필요 없씀
        // 유효성 검사
        if(dto.getUsername() == null || dto.getUsername().isEmpty()){
            throw new DataDeliveryException(Define.ENTER_YOUR_LOGIN,
                    HttpStatus.BAD_REQUEST);
        }

        if(dto.getPassword() == null || dto.getPassword().isEmpty()){
            throw new DataDeliveryException(Define.ENTER_YOUR_PASSWORD,
                    HttpStatus.BAD_REQUEST);
        }

        if(dto.getFullname() == null || dto.getFullname().isEmpty()){
            throw new DataDeliveryException(Define.ENTER_YOUR_FULLNAME,
                    HttpStatus.BAD_REQUEST);
        }
        userService.createUser(dto);
        // TODO : /account/list 경로로 변경해야 함
        return "redirect:/user/sign-in";
    }

    // 로그인 화면 요청
    @GetMapping("/sign-in")
    public String signInPage(){
        log.info("🧤로그인 페이지 임미도토리묵 --! signInPage()");
        return "/user/signIn";
    }

    // 로그인 요청 처리
    @PostMapping("/sign-in")
    public String signProc(SignInDTO dto){
        log.info("⏯️ 로그인 요청 처리 - signProc()");
        // 유효성 검사
        // dto.getUsername() 값이 null 이거나 비어있으면 예외 발생
        // null : 객체가 존재하지 않음을 확인
        if (dto.getUsername() == null || dto.getUsername().isEmpty()){
            throw new DataDeliveryException(Define.ENTER_YOUR_USERNAME,
                    HttpStatus.BAD_REQUEST);
        }
        if (dto.getPassword() == null || dto.getPassword().isEmpty()){
            throw new DataDeliveryException(Define.ENTER_YOUR_PASSWORD,
                    HttpStatus.BAD_REQUEST);
        }
        // 사용자 정보 조회
        // userService.readUser(dto) -> 입력된 로그인 정보를 바탕으로 DB 에서 해당 사용자를 찾습니다.
        // User principal : 로그인한 사용자 객체
        // DTO (Data Transfer Object) : 데이터를 이동할 때, 사용되는 객체
        // Service Layer : userService 는 비즈니스 로직을 처리하는 계층으로, 보통 데이터베이스와 상호작용

        User principal = userService.readUser(dto);
        session.setAttribute(Define.PRINCIPAL, principal);
        // Define 에 공통적으로 사용되는 상수 (Constant) 들을 정의
        return "redirect:/account/list";

    }

    // 로그아웃
    @GetMapping("/logout")
    public String logout(HttpSession session, RedirectAttributes redirectAttributes){ // HttpSession 객체를 매개변수로 받아 사용자의 현재 세션을 제어함.
        session.invalidate(); // 현재 사용자의 세션을 무효화(삭제)하여 로그인 상태를 해제합니다.
        // 즉, 사용자가 로그인되어 있었다면? 해당 정보를 삭제하고 강제로 로그아웃됩니다.
        redirectAttributes.addFlashAttribute("logoutMessage", "로그아웃 되었습니다❗️");
        return "redirect:/user/sign-in";
    }










}
