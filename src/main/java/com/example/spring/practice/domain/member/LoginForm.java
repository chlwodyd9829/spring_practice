package com.example.spring.practice.domain.member;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class LoginForm {

    @NotEmpty(message = "아이디 입력")
    private String loginId;
    @NotEmpty(message = "비밀번호 입력")
    private String password;

    public LoginForm(){
    }
    public LoginForm(String loginId, String password) {
        this.loginId = loginId;
        this.password = password;
    }
}
