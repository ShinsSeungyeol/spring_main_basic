package com.example.spring_main_basic.member;

public interface MemberService {
    void join(Member member);
    Member findMember(Long memberId);

}
