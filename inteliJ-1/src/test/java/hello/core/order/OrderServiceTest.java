package hello.core.order;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

// 주문 기능 테스트
public class OrderServiceTest {

    MemberService memberService = new MemberServiceImpl();
    OrderService orderService = new OrderServiceImpl();

    @Test
    void createOrder() {
        //  given 회원 - id : 1, 이름 : memberA, 등급 : vip
        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.vip);
        memberService.join(member);

        // when 주문생성 - 해당 id : 1번 고객(memberA)이 itemA 상품 (10000원)을 주문한다.
        Order order = orderService.createOrder(memberId, "itemA", 10000);

        // then 할인적용 - 주문한 고객이 vip 일 경우 -1000원 할인
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }

}
