package hello.core.discount;

import hello.core.member.Member;
/**
1. 주문 생성: 클라이언트는 주문 서비스에 주문 생성을 요청한다.
 *        2. 회원 조회: 할인을 위해서는 회원 등급이 필요하다. 그래서 주문 서비스는 회원 저장소에서 회원을 조회한다.
 *        3. 할인 적용: 주문 서비스는 회원 등급에 따른 할인 여부를 할인 정책에 위임한다.
 *        4. 주문 결과 반환: 주문 서비스는 할인 결과를 포함한 주문 결과를 반환한다.
 *        > 참고: 실제로는 주문 데이터를 DB에 저장하겠지만, 예제가 너무 복잡해 질 수 있어서 생략하고, 단순히 주문
 *        결과를 반환한다.
*/
// 할인정책 인터페이스
public interface DiscountPolicy {
    /**
     * @return 할인 대상 금액
     */
    int discount(Member member, int price);
}
