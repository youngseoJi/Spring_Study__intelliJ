package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;

// 정액 할인 정책 구현체
public class FixDiscountPolicy implements  DiscountPolicy {

    // 정액 할인가 : Vip 등급일 경우 => 1000원 할인
    private int discountFixAmount = 1000;
    @Override
    public int discount(Member member, int price) {
        // 조건1. 회원이 등급이 Vip 일 경우
        if (member.getGrade() == Grade.vip){
            // 정액할인 o
            return discountFixAmount;
        // 조건2. 회원이 등급이 Vip가 아닌 경우
        }  else {
            // 할인 X
            return 0;
        }
    }
}
