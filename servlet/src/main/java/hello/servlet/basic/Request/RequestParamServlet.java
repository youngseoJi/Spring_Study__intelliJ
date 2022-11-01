package hello.servlet.basic.Request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

/**
 * 1. 파타미터 전송기능
 * http://localhost:8080/request-param?username=hello&age=20
 */

@WebServlet(name = "requestParamServlet", urlPatterns = "/request-param")
public class RequestParamServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("[전체 파라미터 조회] -- start");
        // Enumeration<String> parameterNames = request.getParameterNames();// 예전방식모든 파라미터를 꺼냄

        request.getParameterNames().asIterator()       // pramName : username, age  / request.getParameter(paramName) : hello, 20
                        .forEachRemaining(paramName -> System.out.println(paramName + " = " + request.getParameter(paramName)));

        System.out.println("[전체 파라미터 조회] -- end");
        System.out.println();
        // request.getParameterNames(); 모든 파라미터 명을 꺼냄
        // request.getParameter() : 파라미터의 값 조회
        System.out.println("[단일 파라미터 조회]");
        String username = request.getParameter("username");
        System.out.println("username = " + username);

        String age = request.getParameter("age");
        System.out.println("age = " + age);
        System.out.println();

        //getParameterValues : 복수 조회
        System.out.println("[같은 이름의 복수 파라미터 조회]"); 

        String[] usernames = request.getParameterValues("username");
        for (String name : usernames) {
            System.out.println("name = " + name);
        }

        response.getWriter().write("ok"); // 화면에 출력되는 메세지
    }
}
