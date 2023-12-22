package com.example.spring_main_basic.discount;

import com.example.spring_main_basic.member.Member;

public interface DiscountPolicy {
    /**
     *
     * @param member
     * @param price
     * @return 할인 금액
     */
    int discount(Member member, int price);
}

