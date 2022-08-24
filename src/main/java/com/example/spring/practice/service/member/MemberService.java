package com.example.spring.practice.service.member;

import com.example.spring.practice.domain.member.Member;

import java.util.Optional;

public interface MemberService {
    public Member login(String loginId, String password);
}
