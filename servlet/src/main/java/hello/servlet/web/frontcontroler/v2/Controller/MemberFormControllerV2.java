package hello.servlet.web.frontcontroler.v2.Controller;

import hello.servlet.web.frontcontroler.MyView;
import hello.servlet.web.frontcontroler.v2.ControllerV2;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MemberFormControllerV2 implements ControllerV2 {

    @Override
    public MyView process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       /* String viewPath = "/WEB-INF/views/new-form.jsp";
        RequestDispatcher dispatcher = req.getRequestDispatcher(viewPath);
        dispatcher.forward(req, resp);
        기존 코드, 중복되는 코드부분 제거하기*/

        // 리팩토링
        return new MyView("/WEB-INF/views/new-form.jsp");

    }
}
