package hello.servlet.web.frontcontroller.v2.Controller;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v2.ControllerV2;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MemberSaveControllerV2 implements ControllerV2 {
    private MemberRepository memberRepository = MemberRepository.getInstance(); //회원저장소 갖고오기

    @Override
    public MyView process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        int age = Integer.parseInt(req.getParameter("age")); // getParameter 응답결과는 항상 문자 -> 수 int 타입 변환하기
        // 비즈니스 로직
        Member member = new Member(username, age);
        memberRepository.save(member);

        //Model 에 데이터를 보관한다.
        req.setAttribute("member", member);
/*
        String viewPath = "/WEB-INF/views/save-result.jsp"; // 저장결과 호출할 jsp 경로
        RequestDispatcher dispatcher = req.getRequestDispatcher(viewPath); // controler -> view 로 넘김
        dispatcher.forward(req,resp); // 서버내 재호출 -> viewPath jsp 경로
        기존코드 중복코드 제거
*/     // 리팩토링
        return new MyView("/WEB-INF/views/save-result.jsp");
    }
}
