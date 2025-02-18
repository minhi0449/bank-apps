package com.applus_bank.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/*
    날짜 : 2025.02.17 (일)
    이름 : 김민희
    내용 :
 */

// SSR 으로 랜더링 됨
@Controller // IoC 대상 (싱글톤 패턴 관리가 된다.) --> 제어의 역전
public class MainController {

    // REST API 기반으로 주소 설계 가능

    /**
     * 주소설계
     * http:localhost:8080/main-page
     * @return
     */
    @GetMapping({"/main-page", "/index", "/"})
    public String mainPage(){
        return "/main";
    }

}
