package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;

// 정률 할인정책
public class RateDiscountPolicy implements DiscountPolicy {

    // 할인률 10%
    private int discountPercent = 10;
    @Override
    public int discount(Member member, int price) {
        // 회원의 등급이 == vip 일경우
        if (member.getGrade() == Grade.vip) {
            // 가격에서 10% 를 할인
            return price * discountPercent / 100;
        }
        // 회원의 등급이 vip가 아닌 경우 할인 X
        else  {
            return 0;
        }
    }
}
