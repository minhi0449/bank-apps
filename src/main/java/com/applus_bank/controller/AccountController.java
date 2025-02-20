package com.applus_bank.controller;

import com.applus_bank.repository.model.User;
import com.applus_bank.service.AccountService;
import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
@RequestMapping("/account")
@Log4j2
public class AccountController {
    // final 처리
    private final HttpSession session;
    public final AccountService accountService;

    // 생성자 의존 주입 - DI 처리
    public AccountController(HttpSession session, AccountService accountService){
        this.session = session;
        this.accountService = accountService;
    }

    // 계좌 목록 페이지
//    @GetMapping("/list")
//    public String listPage(Model model, @SessionAttribute("principal") User principal){
//        log.info(" 🆗 계좌 목록 페이지 listPage()");
//
//    }



}
