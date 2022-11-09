package hello.servlet.web.frontcontroller.v3.controller.v1.Controller;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import hello.servlet.web.frontcontroller.v3.controller.v1.ControllerV1;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
// 각 컨트롤러 들이 돌일한 인터페이스를 상속받음 - 로직의 일관성
public class MemberListControllerV1 implements ControllerV1 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 모든 회원을 members 배열(Member 타입)에 담아준다.
        List<Member> members = memberRepository.findAll();
        // model에 데이터 보관 - 키 "members" : 값 members
        req.setAttribute("members", members);

        String viewPath = "/WEB-INF/views/members.jsp";
        RequestDispatcher dispatcher = req.getRequestDispatcher(viewPath);
        dispatcher.forward(req, resp);

    }
}
