package com.example.spring.practice.service.member;

import com.example.spring.practice.domain.member.Member;

import java.util.Optional;

public interface MemberService {
    public Optional<Member> login(String loginId, String password);
}
