package com.example.spring.practice.service.member;


import com.example.spring.practice.domain.member.Classification;
import com.example.spring.practice.domain.member.JoinForm;
import com.example.spring.practice.domain.member.Member;
import com.example.spring.practice.domain.member.UpdateMember;
import com.example.spring.practice.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
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
        Optional<Member> findMember = memberRepository.findById(joinForm.getId());
        if(!findMember.isEmpty()){
            return null;
        }
        Member member = new Member(joinForm.getId(), joinForm.getPassword(), joinForm.getName(), joinForm.getAddress(), Classification.USER);
        Member saveMember = memberRepository.save(member);
        return saveMember;
    }

    @Override
    public Member member(String id) {
        return memberRepository.findById(id).orElse(null);
    }

    @Override
    public void update(UpdateMember updateMember) {
        Member member = new Member(updateMember.getId(), updateMember.getPassword(), updateMember.getName(), updateMember.getAddress(), updateMember.getClassification());
        memberRepository.update(member);
    }
}
