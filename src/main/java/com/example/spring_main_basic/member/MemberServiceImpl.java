package com.example.spring_main_basic.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceImpl implements  MemberService {

    private final MemberRepository memberRepository;

    @Autowired // ac.getBean(MemberRepository.class) 의존관계를 자동으로 주입해준다.
    public MemberServiceImpl(MemberRepository memberRepository) {
        // 생성자 주입
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);

    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }


    //FOR TEST
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
