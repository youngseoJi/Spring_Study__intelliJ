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
import java.util.List;

@WebServlet(name = "mvcMemberListServlet", urlPatterns = "/servlet-mvc.memvers")
public class MvcMemberListServlet extends HttpServlet {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 모든 회원을 members 배열(Member 타입)에 담아준다.
        List<Member> members = memberRepository.findAll();
        // model에 데이터 보관,ㄴ담기 - 키 "members" : 값 members
        req.setAttribute("members", members);

        String viewPath = "WEB-INF/views.members.jsp";  // WEB-INF 폴더 내 파일 -> 직접 view로 못가게함.
        RequestDispatcher dispatcher = req.getRequestDispatcher(viewPath);
        dispatcher.forward(req,resp);
    }
}
