package hello.servlet.basic.response;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "responseHeaderServlet", urlPatterns = "/response-header" )
public class ResponseHeaderServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // start line
        response.setStatus(HttpServletResponse.SC_OK); // 200
        // response-header
        response.setHeader("Content-Type", "text/plain;charset=utf-8");
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // 캐쉬를 완전히 무효화 
        response.setHeader("pragma","no-cache"); // 캐쉬 무효화(과거버전까지)
        response.setHeader("my-header", "hello"); // 임의 헤더생성 가능

        //[Header 편의 메서드]
        content(response);
        cookie(response);
        //redirect(response);

        // [message body]
        PrintWriter writer = response.getWriter();
        writer.print("ok");

    }
    private void content(HttpServletResponse response) {
        //Content-Type: text/plain;charset=utf-8
        //Content-Length: 2 - 문자 길이 임의로 설정할경우
        //response.setHeader("Content-Type", "text/plain;charset=utf-8");
        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");
        //response.setContentLength(2); //(생략시 자동 생성) - 일반적으로 생략한다.
    }

    // 쿠키 생성
    private void cookie(HttpServletResponse response) {
        //Set-Cookie: myCookie=good; Max-Age=600;
        //response.setHeader("Set-Cookie", "myCookie=good; Max-Age=600");
        Cookie cookie = new Cookie("myCookie", "good");
        cookie.setMaxAge(600); //600초
        response.addCookie(cookie);
    }
        private void redirect(HttpServletResponse response) throws IOException {
            //Status Code 302
            //Location: /basic/hello-form.html
            //response.setStatus(HttpServletResponse.SC_FOUND); -> 302
            //response.setHeader("Location", "/basic/hello-form.html");
            response.sendRedirect("/basic/hello-form.html"); // 리다이렉트될 주소 작성 -상태값 자동
        }

}
