package hello.servlet.web.springmvc.old;

import org.springframework.stereotype.Component;
import org.springframework.web.HttpRequestHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component("/springmvc/request-handler") // 스프링 빈 이름 등록
public class MyHttpRequesthandeler implements HttpRequestHandler {

    // 스프링빈 이름으로 핸들러를 찾음 HttpRequestHandler -> 핸들러 어댑터 찾기 HttpRequestHandlerAdapter -> 핸들러 어댑터 실행
    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("MyHttpRequesthandeler.handleRequest");
    }
}
