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
    public Optional<Member> login(String loginId, String password) {
        Optional<Member> member = memberRepository.findById(loginId);
        if(member.get().getPassword().equals(password)){
            return member;
        }
        return null;
    }
}
