package hello.servlet.web.servletmvc.servlet;

import hello.servlet.domain.member.MemberRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

// servlet 으로 생성 -> 자바코드를 + 다 더해야하기 때문에 불편
@WebServlet(name = "memberFormServlet", urlPatterns = "/servlet/members/new-form")
public class memberFormServlet extends HttpServlet {

    // private로 생성된 인스턴스는, 해당 클래스에서 선언된 함수로만 조회가능  new 생성 불가
    // MemberRepository 저장소 갖고옴
    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html");
        resp.setCharacterEncoding("utf-8");

        PrintWriter w = resp.getWriter();
        // 페이지 소스 나옴
        w.write("<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                " <meta charset=\"UTF-8\">\n" +
                " <title>Title</title>\n" +
                "</head>\n" +
                "<body>\n" +
                // /servlet/members/save 이 경로에 멤버가 저장되게 해둠 -> 회원저장되는 곳 생성
                "<form action=\"/servlet/members/save\" method=\"post\">\n" +
                " username: <input type=\"text\" name=\"username\" />\n" +
                " age: <input type=\"text\" name=\"age\" />\n" +
                " <button type=\"submit\">전송</button>\n" +
                "</form>\n" +
                "</body>\n" +
                "</html>\n");
    }
}
