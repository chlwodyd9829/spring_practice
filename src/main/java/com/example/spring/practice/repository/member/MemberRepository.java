package com.example.spring.practice.repository.member;

import com.example.spring.practice.domain.member.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    public Member save(Member member);
    public Optional<Member> findById(String LoginId);

    public List<Member> findAll();

    public List<String> getColNames();
}
