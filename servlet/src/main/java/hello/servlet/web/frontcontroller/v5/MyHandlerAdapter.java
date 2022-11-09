package hello.servlet.web.frontcontroller.v5;

import hello.servlet.web.frontcontroller.ModelView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// 인터페이스 : 구조 동일화
// 어떤 컨트롤러를 지원할 수 있는지 판단해주는 함수 
// 실제 실행하는 함수
public interface MyHandlerAdapter {

    boolean supports(Object handler); // 컨트롤러 지원할 수 있는지 boolean true or false

    ModelView handle(HttpServletRequest req, HttpServletResponse resp, Object handler) throws ServletException, IOException;


}
