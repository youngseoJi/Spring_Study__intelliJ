package hello.core;


import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// AppConfig
// 애플리케이션의 전체 동작 방식을 설정,구성하는 클래스
// 1. 실제 동작에 필요한 구현객체 생성
// 2. 생성한 객체 인스턴스의 참조(레퍼런스)를 생성자를 통해서 주입(연결)
// 역할과 구현클래스가 한눈에 들어오게! 애플리케이션 전체구성이 어떻게 되었는지 빠르게 파악하기 좋다.

@Configuration
public class AppConfig {
    /** 스프링 빈 사용하기
     * @Bean 스프링 빈: 스프링 컨테이너에 등록된다. ex) memberService(), memberRepositrory() ..등
     * 스프링 빈 이름 짓기: 1. @Bean 이 붙은 메서드 명, 2. @Bean(name="빈이름2") 직접 지정해주기
     * 주의 : 빈 이름은 모두 다른 이름 지정! - 같은 이름일 경우 무시되거나 덮어지면서 오류발생
     * 스프링 컨테이너는 설정 정보(구성요소)를 참고해서 의존관계를 주입(DI)함 */
    // 각각 다른 두개의 객체 생성 - 싱글톤패턴이 꺠지는 것 같다.
    // @Bean memberService 빈생성시-> new MemoryMemberRepository() 한번 호출해준다.
    // @Bean orderService 빈생성시-> new MemoryMemberRepository() 한번 호출해준다.

    /** 호출 순서, 왜 한번씩만 호출되는가? 스프링은 어떻게든 싱글톤패턴을 유지해준다!
    * call AppConfig.memberService
    * call AppConfig.memberRepository
    * call AppConfig.orderService */
    @Bean // 스프링 빈
    // 회원서비스 생성
    // config를 통해서 memberService를 불러서 사용한다.
    public MemberService memberService() {
        // 생성자주입 MemberServiceImpl를 통해 MemberRepository을 주입안다.
        System.out.println("call AppConfig.orderService");
        System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    }
    @Bean
    public MemberRepository memberRepository() {
        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }
    @Bean
    // 주문
    // config를 통해서 orderService를 조회하면
    public OrderService orderService() {
        // OrderServiceImpl 반환되는데 두 memberRepositrory, discountPolicy 가 객체 의존관계로 주입된다.
        System.out.println("call AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
//        return null;
    }
    @Bean
    // 정액할인 정택을 정률할인 정책으로 또는 그 반대로 변경한다면? 로직 사용영역 코드가
    // 아닌, 아래 처럼 구성요소 부분만 변경하면 되게 설계되어있다!
    public DiscountPolicy discountPolicy() {
        //  return new FixDiscountPolicy();    //  할인정책은, 정액할인정책을 사용한다. - 역할
          return new RateDiscountPolicy();     //  할인정책은, 정률할인정책을 사용한다. - 역할
    }
}
