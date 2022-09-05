package com.example.spring.practice.service.member;

import com.example.spring.practice.domain.member.JoinForm;
import com.example.spring.practice.domain.member.Member;
import com.example.spring.practice.domain.member.UpdateMember;

import java.util.List;

public interface MemberService {
    public Member login(String loginId, String password);

    public Member member(String id);
    public List<Member> members();

    public List<String> colNames();

    public Member join(JoinForm joinForm);

    public void update(UpdateMember updateMember);
}
