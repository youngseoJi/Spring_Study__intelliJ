package hello.servlet.web.servletmvc;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "mvcMemberSaveServlet", urlPatterns = "/servlet-mvc/members/save")
public class MvcMemberSaveServlet extends HttpServlet {

    private MemberRepository memberRepository = MemberRepository.getInstance(); //회원저장소 갖고오기

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        String username = req.getParameter("username");
        int age = Integer.parseInt(req.getParameter("age"));
        // getParameter 응답결과는 항상 문자 -> 수 int 타입 변환하기

        // 비즈니스 로직
        Member member = new Member(username, age); // 회원 생성
        memberRepository.save(member); // 저장, 회원가입

        //Model 에 데이터를 보관한다.
        req.setAttribute("member", member);

        String viewPath = "/WEB-INF/view/save-result.jsp"; // 저장결과 호출할 jsp 경로
        RequestDispatcher dispatcher = req.getRequestDispatcher(viewPath); // controler -> view 로 넘김
        dispatcher.forward(req,resp); // 서버내 재호출 -> viewPath jsp 경로
    }
}
