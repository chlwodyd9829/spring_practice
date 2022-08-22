package com.example.spring.practice.controller;

import com.example.spring.practice.domain.member.LoginForm;
import com.example.spring.practice.domain.member.Member;
import com.example.spring.practice.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Optional;

@Slf4j
@Controller
@RequiredArgsConstructor
public class AdminController {
    private final MemberService memberService;
    @GetMapping("/login")
    public String login(Model model){
        model.addAttribute("loginForm",new LoginForm());
        return "login";
    }

    @ResponseBody
    @PostMapping("/login")
    public String login(@Validated @ModelAttribute LoginForm loginForm, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "login";
        }
        Optional<Member> member = memberService.login(loginForm.getLoginId(), loginForm.getPassword());
        if(member.equals(null)){
            return "없는 사용자";
        }
        return "로그인";
    }
}
