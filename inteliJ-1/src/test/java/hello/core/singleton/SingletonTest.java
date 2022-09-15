package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
/**       스프링 없는 순수한 DI 컨테이너인 AppConfig는 요청을 할 때 마다 객체를 새로 생성한다.
          고객 트래픽이 초당 100이 나오면 초당 100개 객체가 생성되고 소멸된다! -> 메모리 낭비가 심하다.
          해결방안은 해당 객체가 딱 1개만 생성되고, 공유하도록 설계하면 된다. -> 싱글톤 패턴 */
public class SingletonTest {

    @Test
    @DisplayName("스프링 없는 순수 DI컨테이너")
    void pureContainer(){
        AppConfig appConfig = new AppConfig();
        // 1. 조회 :  호출할때마다 객체생성
        MemberService memberService1 = appConfig.memberService();

        // 2. 조회 :  호출할때마다 객체생성
        MemberService memberService2 = appConfig.memberService();

        // 참조값 다름을 확인
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        // 테스트 확인 : 두 멤버서비스는 달라야 함 memberService1 !== memberService2
        assertThat(memberService1).isNotSameAs(memberService2);
    }

    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체 사용")
    // 호출
    void singletonServiceTest() {
        // 있는 객체 SingleTonService 를 활용, 새롭게 생성 X 호출할 때 마다 같은 객체 인스턴스를 반환하는 것을 확인할 수 있다.
        SingleTonService singletonService1 = SingleTonService.getInstance();
        SingleTonService singletonService2 = SingleTonService.getInstance();
        //테스트 확인 : 두 싱글톤서비스는 달라야 함  singletonService1 !==  singletonService2
        System.out.println("singletonService1 = " + singletonService1);
        System.out.println("singletonService2 = " + singletonService2);

        assertThat(singletonService1).isSameAs(singletonService2);
        // same == 참조를 비교, 참조객체, 인스턴스 비교 완전히 같은지
        // equal 이퀄즈 비교하는것
    }
}
