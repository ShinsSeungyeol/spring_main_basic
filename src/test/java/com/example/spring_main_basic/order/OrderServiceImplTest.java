package com.example.spring_main_basic.order;

import com.example.spring_main_basic.discount.FixDiscountPolicy;
import com.example.spring_main_basic.member.Grade;
import com.example.spring_main_basic.member.Member;
import com.example.spring_main_basic.member.MemberRepository;
import com.example.spring_main_basic.member.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceImplTest {
    @Test
    void createOrder() {
        MemberRepository memberRepository = new MemoryMemberRepository();
        memberRepository.save(new Member(1L, "name", Grade.VIP));

        OrderServiceImpl orderService = new OrderServiceImpl(new MemoryMemberRepository(), new FixDiscountPolicy());

        Order order = orderService.createOrder(1L, "itemA", 10000);
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }

}