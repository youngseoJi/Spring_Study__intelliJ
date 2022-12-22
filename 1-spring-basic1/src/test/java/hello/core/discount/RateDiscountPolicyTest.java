package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

// TEST시, org.assertj.core.api.Assertions.assertThat 을 쓰는 이유! https://jwkim96.tistory.com/168


class RateDiscountPolicyTest {

    RateDiscountPolicy discountPolicy = new RateDiscountPolicy();

    @Test
    @DisplayName("VIP는 10% 할인이 적용되어야 한다.")

    void vip_o() {
        //given
        Member member = new Member(1L, "memberVIP", Grade.vip);
        //when 10000원 구매시 ? 할인된 후의 값
        int discount = discountPolicy.discount(member, 10000);
        //then 검증 / discount - 1000원 할인이 적용되어야한다.
        assertThat(discount).isEqualTo(1000);
        // Assertions.assertThat
    }

    @Test
    @DisplayName("VIP가 아니면 할인이 적용안된다.")

    void vip_X() {
        //given
        Member member = new Member(2L, "memberBASIC", Grade.basic);
        //when 10000원 구매시 ? 할인된 후의 값
        int discount = discountPolicy.discount(member, 10000);
        //then 검증 / discount - 할인이 적용되지 않아야한다. 0원이어야한다.
        assertThat(discount).isEqualTo(0);
    }
}