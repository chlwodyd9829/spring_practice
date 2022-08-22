package com.example.spring.practice.domain.member;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Member {
    private String id;
    private String password;

    public Member(){}

    public Member(String id, String password) {
        this.id = id;
        this.password = password;
    }

}
