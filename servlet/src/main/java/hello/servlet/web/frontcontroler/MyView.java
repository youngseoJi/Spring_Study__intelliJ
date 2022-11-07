package hello.servlet.web.frontcontroler;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// View  : 화면을 랜더링 하기 위핸 MyView객체 생성
public class MyView {
    private String viewPath; // "/WEB-INF/views/new-form.jsp"

    // 생성자
    public MyView(String viewPath) {
        this.viewPath = viewPath;
    }

    // 랜더링 함수 : view를 만드는
    public void render(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher(viewPath); // ("/WEB-INF/views/new-form.jsp")
        dispatcher.forward(req,resp); // 화면 랜더링 끝
    }

}
