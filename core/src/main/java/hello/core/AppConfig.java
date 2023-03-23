package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPollicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/** AppConfig(기획자)를 통해 관심사 분리 */
// 스프링 쓰기 전
//public class AppConfig {
//
////    // 아래 방식대로 하면, 설정 정보를 담는 곳이므로 역할에 따른 구현이 한눈에 보여야 하는데 그렇지 못함
////    public MemberService memberService(){
////        return new MemberServiceImpl(new MemoryMemberRepository());
////    }
////
////    public OrderService orderService(){
////        return new OrderServiceImpl(new MemoryMemberRepository(), new FixDiscountPollicy());
////    }
//
//    /** 리팩토링 (Ctrl + Alt + M) */
//    // 결과
//    // 1. new MemoryMemberRepository() 이 부분이 중복 제거 (다른 구현체로 변경할 때 한 부분만 변경하면 됨)
//    // 2. AppConfig 를 보면 역할과 구현 클래스가 한눈에 들어옴 (전체 구성 빠르게 파악 가능)
//    public MemberService memberService(){
//        return new MemberServiceImpl(memberRepository());
//    }
//
//    public MemoryMemberRepository memberRepository() {
//        return new MemoryMemberRepository();
//    }
//
//    public OrderService orderService(){
//        return new OrderServiceImpl(memberRepository(), discountPolicy());
//    }
//
//    public DiscountPolicy discountPolicy(){
////        return new FixDiscountPollicy();
//        return new RateDiscountPolicy();
//    }
//}


/** 스프링이 Bean을 등록하는 방법 2 : 팩토리 메소드 이용 */
@Configuration
public class AppConfig {
    /*
    @Bean memberService -> memberRepository()을 호출하면서, 결과적으로 new MemoryMemberRepository()를 호출
    @Bean orderService -> memberRepository()을 호출하면서, 결과적으로 new MemoryMemberRepository()를 호출
    두번 호출 -> 싱글톤 깨질까?

    출력문 추가
    < 예상 시나리오 > 3번 호출 => 5번
    call AppConfig.memberService
    call AppConfig.memberRepository
    call AppConfig.memberRepository
    call AppConfig.orderService
    call AppConfig.memberRepository

    < 실제 출력 > 3번
    call AppConfig.memberService
    call AppConfig.memberRepository
    call AppConfig.orderService
    */

    @Bean
    public MemberService memberService(){
        System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    }
    @Bean
    public MemoryMemberRepository memberRepository() {
        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }
    @Bean
    public OrderService orderService(){
        System.out.println("call AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }
    @Bean
    public DiscountPolicy discountPolicy(){
//        return new FixDiscountPollicy();
        return new RateDiscountPolicy();
    }
}