package hello.core.autowired;

import hello.core.AutoAppConfig;
import hello.core.MemberApp;
import hello.core.discount.DiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 조회한 빈이 모두 필요할 때, List, Map 연습해보기
 * 의도적으로 정말 해당 타입의 스프링 빈이 다 필요한 경우도 있다.
 * 예를 들어서 할인 서비스를 제공하는데, 클라이언트가 할인의 종류(rate, fix)를 선택할 수 있다고
 * 가정해보자. 스프링을 사용하면 소위 말하는 전략 패턴을 매우 간단하게 구현할 수 있다.
 * */
public class AllBeanTest {

    @Test
    void findAllBean(){
//       AutoAppConfig, DiscountService 를 스프링 빈으로 등록한다.
      ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class, DiscountService.class);

      DiscountService discountService = ac.getBean(DiscountService.class);
        Member member = new Member(1L, "userA", Grade.vip);
        int discountPrice = discountService.discount(member, 10000, "fixDiscountPolicy");

        assertThat(discountService).isInstanceOf(DiscountService.class);
        assertThat(discountPrice).isEqualTo(1000);

        int rateDiscountPrice = discountService.discount(member, 20000, "rateDiscountPolicy");
        assertThat(rateDiscountPrice).isEqualTo(2000);
    }

    static class  DiscountService {
        // DiscountService는 Map으로 모든 DiscountPolicy 를 주입받음 -> 이때 fixDiscountPolicy, rateDiscountPolicy 가 주입된다.
        private final Map<String, DiscountPolicy> policyMap; // policyMap 에 주입된 것 !!
        //Map  key, value
//        policyMap = {fixDiscountPolicy=hello.core.discount.FixDiscountPolicy@302a07d, rateDiscountPolicy=hello.core.discount.RateDiscountPolicy@5cdd09b1}
        private final List<DiscountPolicy> policies;
        //List  value
        // policies = [hello.core.discount.FixDiscountPolicy@302a07d, hello.core.discount.RateDiscountPolicy@5cdd09b1]

        @Autowired // 생성자가 1개여서 생략가능
        // DiscountService 빈이 자동관계주입이 될때 AutoAppConfig는 컴포넌트 스캔을 한다. 스프링빈에 등록된 rate, fix 디스카운트를 등록한다.
        public DiscountService(Map<String, DiscountPolicy> policyMap, List<DiscountPolicy> policies) {
            this.policyMap = policyMap;
            this.policies = policies;
            System.out.println("policyMap = " + policyMap);
            System.out.println("policies = " + policies);
        }

        // discount 메소드 생성
        // discountCode로 "fixDiscountPolicy"가 넘어오면 ->  map 에서 fixDiscountPolicy 스프링 빈을 찾아서 실행
        // discountCode로 “rateDiscountPolicy”가 넘어오면 ->  map 에서 rateDiscountPolicy 스프링 빈을 찾아서 실행
        public int discount(Member member, int price, String discountCode) {
            DiscountPolicy discountPolicy = policyMap.get(discountCode);
            return discountPolicy.discount(member,price);
        }
    }
}
   /**  주입 분석
        Map<String, DiscountPolicy> : map의 키에 스프링 빈의 이름을 넣어주고, 그 값으로
        DiscountPolicy 타입으로 조회한 모든 스프링 빈을 담아준다.
        List<DiscountPolicy> : DiscountPolicy 타입으로 조회한 모든 스프링 빈을 담아준다.
        만약 해당하는 타입의 스프링 빈이 없으면, 빈 컬렉션이나 Map을 주입한다. */