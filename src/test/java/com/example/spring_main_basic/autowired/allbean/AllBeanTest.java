package com.example.spring_main_basic.autowired.allbean;

import com.example.spring_main_basic.AutoAppConfig;
import com.example.spring_main_basic.discount.DiscountPolicy;
import com.example.spring_main_basic.member.Grade;
import com.example.spring_main_basic.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Map;

public class AllBeanTest {

    @Test
    void findAllBean() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class, DiscountService.class);
        DiscountService discountService = ac.getBean(DiscountService.class);

        Member member = new Member(1L, "userA", Grade.VIP);
        int fixDiscountPrice = discountService.discount(member, 1000, "fixDiscountPolicy");
        Assertions.assertThat(fixDiscountPrice).isEqualTo(1000);

        int rateDiscountPrice = discountService.discount(member, 1000, "rateDiscountPolicy");
        Assertions.assertThat(rateDiscountPrice).isEqualTo(100);
    }

    static class DiscountService {
        private final Map<String, DiscountPolicy> policyMap;
        private final List<DiscountPolicy> policies;

        public DiscountService(Map<String, DiscountPolicy> policyMap, List<DiscountPolicy> policies) {
            this.policyMap = policyMap;
            this.policies = policies;
            System.out.println("policyMap = " + policyMap + ", policies = " + policies);;
        }

        public int discount(Member member, int price, String policy) {
            DiscountPolicy discountPolicy = policyMap.get(policy);

            return discountPolicy.discount(member, price);
        }
    }
}
