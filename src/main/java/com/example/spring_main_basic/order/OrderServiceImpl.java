package com.example.spring_main_basic.order;

import com.example.spring_main_basic.discount.DiscountPolicy;
import com.example.spring_main_basic.discount.FixDiscountPolicy;
import com.example.spring_main_basic.discount.RateDiscountPolicy;
import com.example.spring_main_basic.member.Member;
import com.example.spring_main_basic.member.MemberRepository;
import com.example.spring_main_basic.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService {
    private  MemberRepository memberRepository;
    private  DiscountPolicy discountPolicy;

    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
