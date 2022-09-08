package hello.core;


import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;


// 애플리케이션의 전체 동작 방식을 설정,구성하는 클래스
// 1. 실제 동작에 필요한 구현객체 생성
// 2. 생성한 객체 인스턴스의 참조(레퍼런스)를 생성자를 통해서 주입(연결)

// 역할과 구현클래스가 한눈에 들어오게! 애플리케이션 전체구성이 어떻게 되었는지 빠르게 파악하기 좋다.
public class AppConfig {
    // 회원서비스 생성
    // config를 통해서 memberService를 불러서 사용한다.
    public MemberService memberService() {
        // 생성자주입 MemberServiceImpl를 통해 MemberRepository을 주입안다.
        return new MemberServiceImpl(memberRepositrory());
    }

    private static MemoryMemberRepository memberRepositrory() {
        return new MemoryMemberRepository();
    }

    // 주문
    // config를 통해서 orderService를 조회하면
    public OrderService orderService() {
        // OrderServiceImpl 반환되는데 두 memberRepositrory, discountPolicy 가 객체 의존관계로 주입된다.
        return  new OrderServiceImpl(memberRepositrory(), discountPolicy());
    }

    // DiscountPolicy  할인정책은, 정액할인정책 (FixDiscountPolicy)을 사용한다. - 역할
    public DiscountPolicy discountPolicy() {
        return new FixDiscountPolicy();
    }
}
