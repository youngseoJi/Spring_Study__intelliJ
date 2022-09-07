package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService{

    private final MemberRepository memberRepository = new MemoryMemberRepository(); // 회원저장소
    private final DiscountPolicy discountPolicy = new FixDiscountPolicy(); // 고정 할인 정책

    @Override
    // 1. 주문생성 요청
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        // 2. 회원 정보 조회
        Member member = memberRepository.findById(memberId); // 회원 조회
        // 3. 할인 정책에 해당 회원 정보를 넘겨준다.  (단일책임원칙이 잘 설계된 경우, 필요할시 추후에 양쪽(정액, 정률 할인정책이아닌 )이 아닌 discountPolicy 정액할인정책만 수정하면된다.)
        int discountPrice = discountPolicy.discount(member, itemPrice);

        // 4. 주문서를 생성
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
