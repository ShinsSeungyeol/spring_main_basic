package com.example.spring_main_basic.discount;

import com.example.spring_main_basic.member.Grade;
import com.example.spring_main_basic.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RateDiscountPolicyTest {

    RateDiscountPolicy discountPolicy = new RateDiscountPolicy();
    @Test
    @DisplayName("VIP는 10% 할인이 적용 되어야 한다.")
    void vipDiscount() {
        Member member = new Member(1L, "memberVIP", Grade.VIP);
        int discount = discountPolicy.discount(member, 10000);
        Assertions.assertThat(discount).isEqualTo(1000);
    }

    @Test
    @DisplayName("VIP가 아니면 할인이 적용 되지 않아야 한다.")
    void basicDiscount() {
        Member member = new Member(2L, "memberVIP", Grade.BASIC);
        int discount = discountPolicy.discount(member, 10000);
        Assertions.assertThat(discount).isEqualTo(0);
    }
}