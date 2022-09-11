package com.example.spring.practice.interceptor;

import com.example.spring.practice.domain.member.Classification;
import com.example.spring.practice.domain.member.Member;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

public class AdminInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession(false);
        Member loginMember = (Member)session.getAttribute("loginMember");
        if(loginMember.getClassification().equals(Classification.USER)){
            session.invalidate();
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter writer = response.getWriter();
            writer.println("<script>alert('권한이 없습니다.'); history.go(-1)</script>");
            writer.flush();
            response.sendRedirect("/login");
            return false;
        }
        return true;
    }
}
