package com.example.spring.practice.controller;

import com.example.spring.practice.domain.member.JoinForm;
import com.example.spring.practice.domain.member.Member;
import com.example.spring.practice.service.item.ItemService;
import com.example.spring.practice.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.MalformedURLException;

@Controller
@Slf4j
@RequiredArgsConstructor
public class UserController {
    private final MemberService memberService;
    private final ItemService itemService;

    @GetMapping("/login")
    public String login(){
        return "user/login";
    }

    @GetMapping("/home")
    public String home(){
        return "user/home";
    }
    @GetMapping("/join")
    public String join(JoinForm joinForm, Model model){
        log.info("join request");
        model.addAttribute("joinForm",new JoinForm());
        return "user/join";
    }
    @PostMapping("/join")
    public String join(@Validated @ModelAttribute JoinForm joinForm, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "user/join";
        }
        Member joinMember = memberService.join(joinForm);
        if(joinMember == null){
            bindingResult.addError(new ObjectError("exist","이미 존재하는 아이디: "+joinForm.getId()));
            return "user/join";
        }
        return "redirect:/home";
    }
    @ResponseBody
    @GetMapping("/image/{filename}")
    public Resource getImage(@PathVariable String filename) throws MalformedURLException {
        String file = itemService.getFile(filename);
        System.out.println("file = " + file);
        return new UrlResource("file:"+ file);
    }
}
