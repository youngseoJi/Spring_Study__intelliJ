package hello.servlet.web.frontcontroller.v3.controller.v1.Controller;

import hello.servlet.web.frontcontroller.v3.controller.v1.ControllerV1;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// ControllerV1 각 컨트롤러 들이 동일한 인터페이스를 상속받음 - 로직의 일관성
public class MemberFormControlerV1 implements ControllerV1 {

    @Override
    public void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String viewPath = "/WEB-INF/views/new-form.jsp";
        RequestDispatcher dispatcher = req.getRequestDispatcher(viewPath);
        dispatcher.forward(req, resp);
    }
}
