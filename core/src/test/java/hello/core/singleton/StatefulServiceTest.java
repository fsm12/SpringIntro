package hello.core.singleton;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class StatefulServiceTest {
    @Test
    void statefulServiceSingleton() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean("statefulService", StatefulService.class);
        StatefulService statefulService2 = ac.getBean("statefulService", StatefulService.class);

//        // ThreadA: A 사용자 10000원 주문
//        statefulService1.order("userA", 10000);
//        // ThreadB: B 사용자 20000원 주문
//        statefulService2.order("userB", 20000);
//
//        // ThreadA: 사용자 A 주문 금액 조회
//        int price = statefulService1.getPrice();
//
//        // ThreadA: 사용자 A는 10000원을 기대했지만, 기대와 다르게 20000원 출력
//        // 중간에 사용자 B가 바꿔버렸기 때문에!
//        System.out.println("price = " + price);
//
//        assertThat(statefulService1.getPrice()).isEqualTo(20000);

        // 해결 방법

        // ThreadA: A 사용자 10000원 주문
        int userAPrice = statefulService1.order("userA", 10000);
        // ThreadB: B 사용자 20000원 주문
        int userBPrice = statefulService2.order("userB", 20000);

        // ThreadA: 사용자 A 주문 금액 조회
        System.out.println("price = " + userAPrice);
    }

    static class TestConfig {
        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }


}