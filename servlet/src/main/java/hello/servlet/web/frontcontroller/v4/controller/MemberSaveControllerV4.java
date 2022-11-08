package hello.servlet.web.frontcontroller.v4.controller;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.v3.ControllerV3;
import hello.servlet.web.frontcontroller.v4.ControllerV4;

import java.util.Map;

public class MemberSaveControllerV4 implements ControllerV4 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public String process(Map<String, String> paramMap, Map<String, Object> model) {

        String username = paramMap.get("username");
        int age = Integer.parseInt(paramMap.get("age"));

        Member member = new Member(username, age);
        memberRepository.save(member);
        model.put("member", member);
        // model이 이미 생성되있기에 담아주기
        return "save-result";
        // 뷰 논리이름 반환
        
        /* V3의 아래 코드와 달리, 위의 V4 코드는 모델를 생성해주는 과정을 인테페이스에서 생성해서, 필요없는 반복 코드 제거
        ModelView mv = new ModelView("save-result");
        mv.getModel().put("member", member);
        return mv*/

    }



}
