package com.example.spring.practice.service.member;


import com.example.spring.practice.domain.member.Member;
import com.example.spring.practice.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    public Member login(String loginId, String password) {
        return memberRepository.findById(loginId)
                .filter(m->m.getPassword().equals(password)).orElse(null);
    }
}
