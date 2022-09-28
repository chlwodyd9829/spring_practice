package com.example.spring.practice.service.member;

import com.example.spring.practice.domain.member.JoinForm;
import com.example.spring.practice.domain.member.Member;
import com.example.spring.practice.domain.member.UpdateMember;

import java.util.List;

public interface MemberService {
    //로그인 처리
    public Member login(String loginId, String password);
    //회원 찾기
    public Member member(String id);
    //회원 목록
    public List<Member> members();
    //회원 가입
    public Member join(JoinForm joinForm);
    //회원 수정
    public void update(UpdateMember updateMember);
}
