package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

// 객체지향

// 생성자 주입은 빈이 등록될떄 같이 주입된다. // OrderServiceImpl 생성 빈에 등록하면서 자동주입
@Component // 컴포넌트 스캔의 대상이 되면서 스프링빈에 등록된다.
public class OrderServiceImpl implements OrderService{

    // DIP, 준수하기 - 인테페이스에만 의존하게 변경한 것
    // 불변 의존관계
    private final MemberRepository memberRepository; // 회원저장소 인터페이스
    private final DiscountPolicy discountPolicy; // 할인정책 인터페이스
    //  DIP, 준수안한 것 =>  인터페이스에만 의존하도록 클래스 구현체 X, 관심사 분리! 인터페이스와 구현체 분리
    //    private final DiscountPolicy discountPolicy = new FixDiscountPolicy(); // 정액할인(고정할인) 정책
    //    private final DiscountPolicy discountPolicy = new RateDiscountPolicy(); // 정률할인 정책

//    // 수정자 주입(setter 주입)
//    @Autowired // 의존관계 자동주입
//    public void setMemberRepository(MemberRepository memberRepository) {
//        System.out.println("memberRepository = " + memberRepository);
//        this.memberRepository = memberRepository;
//    }
//    @Autowired // 의존관계 자동주입
//    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
//        System.out.println("discountPolicy = " + discountPolicy);
//        this.discountPolicy = discountPolicy;
//    }

    // OrderServiceImpl 생성자 생성
    //  @Autowired // 생성자 위에 Autowired를 작성하면, 스프링이 이것을 생성할때 자동으로 MemberRepository, DiscountPolicy 모두 주입해준다.
    //생성자가 딱 1개일때는 Autowired가 자동 적용되므로안적어도 된다.
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        //System.out.println("첫번째 액션, 출력 : memberRepository = " + memberRepository);
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
