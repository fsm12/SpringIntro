package hello.core.discount;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component // 컴포넌트 스캔
@Primary // 조회 빈이 2개 이상 => 해결 3. @Primary 사용 : 붙은 애들이 우선권을 가짐
@MainDiscountPolicy
public class RateDiscountPolicy implements DiscountPolicy {
    private int discountPrice = 10;

    @Override
    public int discount(Member member, int price) { // Ctrl + shift + T : 테스트코드 바로 생성
        if(member.getGrade() == Grade.VIP){
            return price * discountPrice / 100;
        }else {
            return 0;
        }
    }
}
