package com.example.spring_main_basic.singleton;

import com.example.spring_main_basic.AppConfig;
import com.example.spring_main_basic.member.MemberRepository;
import com.example.spring_main_basic.member.MemberServiceImpl;
import com.example.spring_main_basic.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ConfigurationSingletonTest {
    @Test
    void configurationTest() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);

        MemberRepository memberRepository = ac.getBean("memberRepository", MemberRepository.class);
        MemberRepository memberRepository1 = memberService.getMemberRepository();
        MemberRepository memberRepository2 = orderService.getMemberRepository();

        System.out.println("memberService -> memberRepository = " + memberRepository1);
        System.out.println("orderService -> orderRepository = " + memberRepository2);
        System.out.println("memberRepository -> memberRepository = " + memberRepository);


        Assertions.assertThat(memberService.getMemberRepository()).isSameAs(memberRepository);
        Assertions.assertThat(orderService.getMemberRepository()).isSameAs(memberRepository);

    }

    @Test
    void configurationDeep() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        AppConfig bean = ac.getBean(AppConfig.class);

        /**
         * 내가 만든 클래스가 아니라 스프링이 CGLIB 라는 바이트 조작 라이브러리를 사용해서 AppConfig를 상속받은 임의의 다른 클래스를 만들고,
         * 그 다른 클래스를 스피링 빈으로 등록한 것이다
         */
        System.out.println("bean = " + bean.getClass());

    }

}
