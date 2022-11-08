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
    // model : Map <key 값 String : value 값 Object 형>
    public void render(Map<String, Object> model, HttpServletRequest req,
                       HttpServletResponse response) throws ServletException, IOException {
        // 모델 값을 요청값에 모두 담아주기
        modelToRequestAttribute(model, req);
        RequestDispatcher dispatcher = req.getRequestDispatcher(viewPath);
        dispatcher.forward(req, response); // viewPath url 에 해당되는 jsp로 보내 뷰 랜더링

    }

    // model 에 있는 데이터를 모두 꺼내서 -> HttpServletRequest 요청 값에 다 담아준다. setAttribute key, value 형식으로!
    private void modelToRequestAttribute(Map<String, Object> model, HttpServletRequest req) {
        model.forEach((key, value) -> req.setAttribute(key, value));// 요청값 다 담아두기
    }

}
