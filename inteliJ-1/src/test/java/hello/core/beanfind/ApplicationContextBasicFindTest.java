package hello.core.beanfind;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
//import org.assertj.core.api.Assertions;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
//Assertions 가 import 되지 않았던 오류 - src / test 안에 있어야하는데 main 에 위치해있어서 이동하니 해결
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationContextBasicFindTest {
    // 스프링 컨테이너에서 스프링 빈을 찾는 방법 : ac.getBean(빈이름, 타입) , ac.getBean(타입)
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
    @Test
    @DisplayName("빈 이름으로 조회")
    void findBeanByName(){
        MemberService memberService = ac.getBean("memberService", MemberService.class); // 타입 인터페이스 명
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("타입으로 조회")
    void findBeanByType(){
        MemberService memberService = ac.getBean(MemberService.class); // 타입 인터페이스 명
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }
    @Test
    @DisplayName("구체 타입으로 조회")
        //  인터페이스 내에 객체로 조회 : 하지만 안좋은 방법, 역할과 구분을 하고 역할에 의존해야하는데 구현에 의존하는 상태인것이다.
    void findBeanByName2(){
        MemberService memberService = ac.getBean("memberService", MemberServiceImpl.class); // 인터페이스 내에 등록되어있는 객체로 조회
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("조회 대상 스프링 빈 조회X")
    void findBeanByNameX(){
//        MemberService xxxx = ac.getBean("XXXX", MemberService.class);
        // assertThrows 테스트검증
        // NoSuchBeanDefinitionException 조회 대상 스프링 빈이 없으면 예외 발생 - 예외가 무조건 실행해야 테스트 성공이다.
        assertThrows(NoSuchBeanDefinitionException.class,
                // 로직을 실행하면
                () ->  ac.getBean("XXXX", MemberService.class));
    }

}
