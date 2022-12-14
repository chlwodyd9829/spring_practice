package com.example.spring.practice.repository.member;

import com.example.spring.practice.domain.member.Member;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    public Member save(Member member);
    public Optional<Member> findById(String LoginId);

    public List<Member> findAll();

    public void update(Member member);
}
