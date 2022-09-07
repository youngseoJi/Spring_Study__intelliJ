package hello.core;


import hello.core.discount.FixDiscountPolicy;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;


// 애플리케이션의 전체 동작 방식을 설정,구성하는 클래스
// 1. 실제 동작에 필요한 구현객체 생성
// 2. 생성한 객체 인스턴스의 참조(레퍼런스)를 생성자를 통해서 주입(연결)
public class AppConfig {
    // 회원서비스 생성
    // config를 통해서 memberService를 불러서 사용한다.
    public MemberService memberService() {
        // 생성자주입 MemberServiceImpl를 통해  MemoryMemberRepository을 주입안다.
        return new MemberServiceImpl(new MemoryMemberRepository());
    }
    // 주문
    // config를 통해서 orderService를 조회하면
    public OrderService orderService() {
        // OrderServiceImpl 반환되는데 두 인터페이스가 인자로 넣어준다.넘어간다.
        return  new OrderServiceImpl(new MemoryMemberRepository(), new FixDiscountPolicy());
    }
}
