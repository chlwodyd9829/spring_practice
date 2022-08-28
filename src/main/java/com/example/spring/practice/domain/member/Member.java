package com.example.spring.practice.domain.member;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Member {
    private String id;
    private String password;

    private String name;

    private String address;

    public Member(){}

    public Member(String id, String password, String name, String address) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.address = address;
    }
}
