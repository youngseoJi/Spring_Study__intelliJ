package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {

    public static void main(String[] args) {
//        AppConfig 이용하여 회원 서비스 생성
//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService(); //MemberServiceImpl이 들어가 있으며, 사용할 저장소 MemoryMemberRepository 주입해놓은 상태
        // MemberService memberService = new MemberServiceImpl();

        // AppConfig에 작성한 메서드 사용하기
        /** ApplicationContext : 스프링 컨테이너, 인터페이스
         * AppConfig.class를 변수로 사용할 경우? spring이 AppConfig에서 생성한 객체들을 스프링빈(컨테이너에) 등록한다.*/
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class); // 애노테이션 기반의 자바 설정클래스 생성 - ApplicationContext 의 구현체
        // 지정한 함수를 가져온다. - 메서드 이름 입력, 타입 멤버서비스.
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);
// member 회원가입 - (id 가 1 (1L L은 Long 타입), 이름 mamberA, 등급은 vip)
        Member member = new Member(1L, "memberA", Grade.vip);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("newMember = " + member); // newMember = hello.core.member.Member@15975490
        System.out.println("findMember = " + findMember); // findMember = hello.core.member.Member@15975490
    }

}
