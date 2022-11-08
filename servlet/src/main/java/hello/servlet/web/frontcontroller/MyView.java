package hello.servlet.web.frontcontroller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

// View  : 화면을 랜더링 하기 위핸 MyView객체 생성
public class MyView {
    private String viewPath;// "/WEB-INF/views/new-form.jsp"
    public MyView(String viewPath) {
        this.viewPath = viewPath;
    }

    // 랜더링 함수 : req, resp
    public void render(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher(viewPath); // ("/WEB-INF/views/new-form.jsp")
        dispatcher.forward(req,resp); // 화면 랜더링 끝
    }
    
    
    // 랜더링 함수 v3 : model, req, resp
    public void render(Map<String, Object> model, HttpServletRequest req,
                       HttpServletResponse response) throws ServletException, IOException {
        modelToRequestAttribute(model, req);
        RequestDispatcher dispatcher = req.getRequestDispatcher(viewPath);
        dispatcher.forward(req, response);

    }

    // model 에 있는 데이터를 요청 값에 다 담아준다.
    private void modelToRequestAttribute(Map<String, Object> model, HttpServletRequest req) {
        model.forEach((key, value) -> req.setAttribute(key, value));// 요청값 다 담아두기
    }

}
