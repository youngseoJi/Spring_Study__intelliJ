package hello.servlet.web.frontcontroler.v2.Controller;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import hello.servlet.web.frontcontroler.MyView;
import hello.servlet.web.frontcontroler.v2.ControllerV2;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class MemberListControllerV2 implements ControllerV2 {
    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public MyView process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    // 모든 회원을 members 배열(Member 타입)에 담아준다.
    List<Member> members = memberRepository.findAll();
    // model에 데이터 보관 - 키 "members" : 값 members
        req.setAttribute("members",members);
/*
    String viewPath = "/WEB-INF/views/members.jsp";
    RequestDispatcher dispatcher = req.getRequestDispatcher(viewPath);
        dispatcher.forward(req,resp);
*/
        return new MyView("/WEB-INF/views/members.jsp");

    }
}
