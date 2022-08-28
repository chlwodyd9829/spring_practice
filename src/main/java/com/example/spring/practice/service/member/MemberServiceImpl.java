package com.example.spring.practice.service.member;


import com.example.spring.practice.domain.member.JoinForm;
import com.example.spring.practice.domain.member.Member;
import com.example.spring.practice.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    public Member login(String loginId, String password) {
        return memberRepository.findById(loginId)
                .filter(m->m.getPassword().equals(password)).orElse(null);
    }

    @Override
    public List<Member> members() {
        return memberRepository.findAll();
    }

    @Override
    public List<String> colNames() {
        return memberRepository.getColNames();
    }

    @Override
    public Member join(JoinForm joinForm) {
        Member member = new Member(joinForm.getId(), joinForm.getPassword(), joinForm.getName(), joinForm.getAddress());
        Member saveMember = memberRepository.save(member);
        return saveMember;
    }
}
