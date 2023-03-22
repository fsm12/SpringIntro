package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;

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
