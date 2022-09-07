package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;

public class MemberApp {

    public static void main(String[] args) {
//        AppConfig 이용하여 회원 서비스 생성
        AppConfig appConfig = new AppConfig();
        MemberService memberService = appConfig.memberService(); //MemberServiceImpl이 들어가 있으며, 사용할 저장소 MemoryMemberRepository 주입해놓은 상태
        // MemberService memberService = new MemberServiceImpl(); 
        //  ctrl + art + v
        // member 회원가입
        // (id 가 1 (1L L은 Long 타입), 이름 mamberA, 등급은 vip)
        Member member = new Member(1L, "memberA", Grade.vip);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("newMember = " + member); // newMember = hello.core.member.Member@15975490
        System.out.println("findMember = " + findMember); // findMember = hello.core.member.Member@15975490
    }

}
