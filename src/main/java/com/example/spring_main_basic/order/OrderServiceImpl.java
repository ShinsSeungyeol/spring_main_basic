package com.example.spring_main_basic.order;

import com.example.spring_main_basic.annotation.MainDiscountPolicy;
import com.example.spring_main_basic.discount.DiscountPolicy;
import com.example.spring_main_basic.discount.FixDiscountPolicy;
import com.example.spring_main_basic.discount.RateDiscountPolicy;
import com.example.spring_main_basic.member.Member;
import com.example.spring_main_basic.member.MemberRepository;
import com.example.spring_main_basic.member.MemoryMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService {
    private  final MemberRepository memberRepository;
    private  final DiscountPolicy discountPolicy;

    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, @MainDiscountPolicy DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    //FOR TEST
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
