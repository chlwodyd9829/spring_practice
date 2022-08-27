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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Controller
@RequiredArgsConstructor
public class AdminController {
    private final MemberService memberService;

    @GetMapping("/")
    public String home(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
        HttpSession session = request.getSession(false);
        if(session == null){
            model.addAttribute("loginForm",new LoginForm());
            response.sendRedirect("login");
            return "login";
        }
        Member loginMember = (Member)session.getAttribute("loginMember");
        model.addAttribute("loginMember",loginMember);

        List<Member> members = memberService.members();
        model.addAttribute("members",members);
        return "admin/admin_home";
    }
    @GetMapping("/login")
    public String login(Model model){
        model.addAttribute("loginForm",new LoginForm());
        return "login";
    }

    @PostMapping("/login")
    public String login(@Validated @ModelAttribute LoginForm loginForm, BindingResult bindingResult, HttpServletRequest request){
        if(bindingResult.hasErrors()){
            return "login";
        }
        Member loginMember = memberService.login(loginForm.getLoginId(), loginForm.getPassword());
        if(loginMember == null){
            bindingResult.reject("loginFail","로그인 실패");
            return "login";
        }
        HttpSession session = request.getSession();
        session.setAttribute("loginMember",loginMember);
        return "redirect:/";
    }
    @PostMapping("/logout")
    public String logout(HttpServletRequest request,@ModelAttribute LoginForm loginForm){
        HttpSession session = request.getSession(false);
        if(session != null){
            session.invalidate();
        }
        return "redirect:/login";
    }
}
