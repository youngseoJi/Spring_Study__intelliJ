package hello.servlet.web.frontcontroller.v4.controller;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.v3.ControllerV3;
import hello.servlet.web.frontcontroller.v4.ControllerV4;

import java.util.List;
import java.util.Map;

public class MemberListControllerV4 implements ControllerV4 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public String process(Map<String, String> paramMap, Map<String, Object> model) {
        List<Member> members = memberRepository.findAll();

        model.put("members", members); // 생성해둔 model 에 바로  키 "members" : 값 members 으로 담아주기
        return  "members"; // 뷰의 논리 이름 반환

    }

   /*  v3 코드
    public ModelView process(Map<String, String> paramMap) {
        List<Member> members = memberRepository.findAll(); // 모든 회원 members 배열에 저장

        ModelView mv = new ModelView("members");
        mv.getModel().put("members", members);

        return mv;
    }*/
}
