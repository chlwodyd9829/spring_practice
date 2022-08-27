package com.example.spring.practice.service.member;

import com.example.spring.practice.domain.member.Member;

import java.util.List;

public interface MemberService {
    public Member login(String loginId, String password);

    public List<Member> members();
}
