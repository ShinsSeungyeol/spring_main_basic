package com.example.spring_main_basic;

import com.example.spring_main_basic.discount.DiscountPolicy;
import com.example.spring_main_basic.discount.FixDiscountPolicy;
import com.example.spring_main_basic.discount.RateDiscountPolicy;
import com.example.spring_main_basic.member.MemberRepository;
import com.example.spring_main_basic.member.MemberService;
import com.example.spring_main_basic.member.MemberServiceImpl;
import com.example.spring_main_basic.member.MemoryMemberRepository;
import com.example.spring_main_basic.order.OrderService;
import com.example.spring_main_basic.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 애플리케이션의 실제 동작에 필요한 구현 객체를 생성한다.
 * 생성자를 통해서 주입 해준다.
 * 의존관계에 대한 고민은 외부에 맡기고 실행에만 집중한다.
 */
@Configuration
public class AppConfig {
    @Bean
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public DiscountPolicy discountPolicy() {
        return new RateDiscountPolicy();
    }
}
