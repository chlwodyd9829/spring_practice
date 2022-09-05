package com.example.spring.practice.domain.member;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class JoinForm {
    @NotEmpty(message = "아이디 입력")
    private String id;
    @NotEmpty(message = "비밀번호 입력")
    private String password;

    @NotEmpty(message = "이름 입력")
    private String name;
    @NotNull(message = "주소 입력")
    private String address;
    public JoinForm(){}

    public JoinForm(String id, String password, String name, String address) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.address = address;
    }
}
