package com.applus_bank.controller;

import com.applus_bank.service.AccountService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/account")
public class AccountController {
    // final 처리
    private final HttpSession session;
    public final AccountService accountService;

    // 생성자 의존 주입 - DI 처리
    public AccountController(HttpSession session, AccountService accountService){
        this.session = session;
        this.accountService = accountService;
    }



}
