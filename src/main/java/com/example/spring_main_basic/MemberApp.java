package com.example.spring_main_basic;

import com.example.spring_main_basic.member.Grade;
import com.example.spring_main_basic.member.Member;
import com.example.spring_main_basic.member.MemberService;
import com.example.spring_main_basic.member.MemberServiceImpl;

public class MemberApp {
    public static void main(String[] args) {
        MemberService memberService = new MemberServiceImpl();

        Member memberA = new Member(1L, "memberA", Grade.VIP);
        memberService.join(memberA);

        Member findMember = memberService.findMember(1L);
        System.out.println("new member = " + memberA.getName());
        System.out.println("find member = " + findMember.getName());
    }
}
