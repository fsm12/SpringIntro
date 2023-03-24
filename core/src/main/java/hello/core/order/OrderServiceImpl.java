package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component // 컴포넌트 스캔
public class OrderServiceImpl implements OrderService{

    // private final MemberRepository memberRepository = new MemoryMemberRepository();
    // private final DiscountPolicy discountPolicy = new FixDiscountPollicy();
    // 할인 정책을 변경하려면, 클라이언트인 OrderServiceImpl 코드를 고쳐야한다
    // private final DiscountPolicy discountPolicy = new RateDiscountPolicy();

    // private final DiscountPolicy discountPolicy; // 인터페이스에만 의존하도록 바꾼 코드 but) NPE 발생
    // 이 문제를 해결하려면, 누군가가 클라이언트인 OrderServiceImpl에 DiscountPolicy의 구현 객체를 대신하고 주입해주어야 함

    // 관심사 분리 - AppConfig에서 주입
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    @Autowired // 컴포넌트 스캔 - 자동 의존관계 주입 마치 [ac.getBean(MemberRepository.class)]처럼 동작
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

    // 싱글톤 유지되는지에 대한 테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
