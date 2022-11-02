package hello.servlet.web.servlet;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

// 회원 저장
@WebServlet(name = "memberSaveServlet", urlPatterns = "/servlet/members/save")
public class MemberSaveServlet extends HttpServlet {

    // 멤버 저장소 객체 갖고오기 (프라이빗 선언된 것 (싱글톤)이여서! new X)
    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("MemberSaveServlet.service");

        String username = req.getParameter("username");
        // String age = req.getParameter("age"); 
        // getParameter 응답결과는 항상 문자 -> 수 int 타입 변환하기
        int age = Integer.parseInt(req.getParameter("age"));


        // 비즈니스 로직
        // 회원 생성
        Member member = new Member(username, age);
        // 저장소에 회원가입, 저장
        memberRepository.save(member);

        resp.setContentType("text/html");
        resp.setCharacterEncoding("utf-8");

        // resp  응답 출력
        PrintWriter w = resp.getWriter();

        // 동적 html 변화되는 값 존재
        w.write("<html>\n" +
                "<head>\n" +
                " <meta charset=\"UTF-8\">\n" +
                "</head>\n" +
                "<body>\n" +
                "성공\n" +
                "<ul>\n" +
                // id, 이름, 나이 출력(조회)
                " <li>id="+member.getId()+"</li>\n" +
                " <li>username="+member.getUsername()+"</li>\n" +
                " <li>age="+member.getAge()+"</li>\n" +
                "</ul>\n" +
                // 만들어 둔 page 링크
                "<a href=\"/index.html\">메인</a>\n" +
                "</body>\n" +
                "</html>");


    }
}
