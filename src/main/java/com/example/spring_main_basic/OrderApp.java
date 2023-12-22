package com.example.spring_main_basic;

import com.example.spring_main_basic.member.Grade;
import com.example.spring_main_basic.member.Member;
import com.example.spring_main_basic.member.MemberService;
import com.example.spring_main_basic.member.MemberServiceImpl;
import com.example.spring_main_basic.order.OrderService;
import com.example.spring_main_basic.order.OrderServiceImpl;
import com.example.spring_main_basic.order.Order;

public class OrderApp {
    public static void main(String[] args) {
        AppConfig appConfig = new AppConfig();
        MemberService memberService = appConfig.memberService();
        OrderService orderService = appConfig.orderService();

        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "itemA", 10000);

        System.out.println("order = " + order);
    }
}
