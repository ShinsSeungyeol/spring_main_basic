package com.example.spring_main_basic.scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import jakarta.inject.Provider;

public class SingletonWithPrototypeTest1 {

    @Test
    void prototypeFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);

        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        prototypeBean1.addCount();
        Assertions.assertThat(prototypeBean1.getCount()).isEqualTo(1);

        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
        prototypeBean2.addCount();
        Assertions.assertThat(prototypeBean2.getCount()).isEqualTo(1);
    }

    @Test
    void singletonClientUsePrototype() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class, ClientBean.class);

        ClientBean clientBean1 = ac.getBean(ClientBean.class);
        ClientBean clientBean2 = ac.getBean(ClientBean.class);

        int count1 = clientBean1.logic();
        Assertions.assertThat(count1).isEqualTo(1);

        int count2 = clientBean2.logic();
        Assertions.assertThat(count2).isEqualTo(1);
    }

    /**
     * 스프링에 의존적
     */
    @Test
    void singletonClientWithObjectProviderUsePrototype() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class, ClientBeanWithObjectProvider.class);

        ClientBeanWithObjectProvider clientBean1 = ac.getBean(ClientBeanWithObjectProvider.class);
        ClientBeanWithObjectProvider clientBean2 = ac.getBean(ClientBeanWithObjectProvider.class);

        int count1 = clientBean1.logic();
        Assertions.assertThat(count1).isEqualTo(1);

        int count2 = clientBean2.logic();
        Assertions.assertThat(count2).isEqualTo(1);

    }

    /**
     * 자바 표준
     */
    @Test
    void singletonClientWithProviderUsePrototype() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class, ClientBeanWithProvider.class);

        ClientBeanWithProvider clientBean1 = ac.getBean(ClientBeanWithProvider.class);
        ClientBeanWithProvider clientBean2 = ac.getBean(ClientBeanWithProvider.class);

        int count1 = clientBean1.logic();
        Assertions.assertThat(count1).isEqualTo(1);

        int count2 = clientBean2.logic();
        Assertions.assertThat(count2).isEqualTo(1);

    }

    @Scope("singleton")
    static class ClientBean {
  //      private final PrototypeBean prototypeBean; // 생성 시점에 주입

        @Autowired
        ApplicationContext ac;

        public int logic() {
            /**
             * 실행해보면 항상 새로운 프로토타입 빈이 생성되는 것을 확인할 수 있다.
             * 의존 관계를 외부에서 주입 받는게 아니라 이렇게 직접 필요한 의존 관계를 찾는 것을 DL 의존관계 탐색이라고 한다.
             * 그런데 이렇게 스프링의 애플리케이션 컨텍스트 전체를 주입받게 되면, 스프링 컨테이너에 종속적인 코드가 되고, 단위 테스트도 어려워 진다.
             */
            PrototypeBean prototypeBean = ac.getBean(PrototypeBean.class);
            prototypeBean.addCount();
            return prototypeBean.getCount();

        }
    }

    @Scope("singleton")
    static class ClientBeanWithObjectProvider {
        @Autowired
        private ObjectProvider<PrototypeBean> prototypeBeansProvider;

        public int logic() {
            PrototypeBean prototypeBean = prototypeBeansProvider.getObject();
            prototypeBean.addCount();
            int count = prototypeBean.getCount();
            return count;
        }
    }

    @Scope("singleton")
    static class ClientBeanWithProvider {
        @Autowired
        private Provider<PrototypeBean> prototypeBeanProvider;

        public int logic() {
            PrototypeBean prototypeBean = prototypeBeanProvider.get();
            prototypeBean.addCount();
            int count = prototypeBean.getCount();
            return count;
        }
    }

    @Scope("prototype")
    static class PrototypeBean {
        private int count = 0;

        public void addCount() {
            count++;
        }

        public int getCount() {
            return count;
        }

        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean.init " + this);

        }

        @PreDestroy
        public void destroy() {
            System.out.println("PrototypeBean.destroy");
        }
    }
}
