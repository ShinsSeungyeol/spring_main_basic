package com.example.spring_main_basic.member;

public interface MemberRepository {
    void save(Member member);
    Member findById(Long memberId);
}
