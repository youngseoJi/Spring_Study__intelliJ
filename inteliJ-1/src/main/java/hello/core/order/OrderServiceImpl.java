package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
// 객체지향
// 주문
public class OrderServiceImpl implements OrderService{

    // DIP, 준수하기 - 인테페이스에만 의존하게 변경한 것
    private final MemberRepository memberRepository; // 회원저장소 인터페이스
    private final DiscountPolicy discountPolicy; // 할인정책 인터페이스
    //  DIP, 준수안한 것 =>  인터페이스에만 의존하도록 클래스 구현체 X, 관심사 분리! 인터페이스와 구현체 분리
    //    private final DiscountPolicy discountPolicy = new FixDiscountPolicy(); // 정액할인(고정할인) 정책
    //    private final DiscountPolicy discountPolicy = new RateDiscountPolicy(); // 정률할인 정책
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }


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

    // 테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
