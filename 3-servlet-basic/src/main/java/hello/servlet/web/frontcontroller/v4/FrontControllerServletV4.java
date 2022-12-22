package hello.servlet.web.frontcontroller.v4;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v4.ControllerV4;
import hello.servlet.web.frontcontroller.v4.controller.MemberFormControllerV4;
import hello.servlet.web.frontcontroller.v4.controller.MemberListControllerV4;
import hello.servlet.web.frontcontroller.v4.controller.MemberSaveControllerV4;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

// frontController v4
// model 및 paramMap 모두 프론트 컨트롤러에서 생성함.
@WebServlet(name = "frontControllerServletV4", urlPatterns = "/front-controller/v4/*")

public class FrontControllerServletV4 extends HttpServlet {

    // url 매핑 정보 생성
    private Map<String, ControllerV4> controllerMap = new HashMap<>();

    public FrontControllerServletV4() {
        // 특정 "url" 이 호출되면 , 해당 컨트롤러 호출 -> 실행 -> controllerMap에 값을 담아준다. -> 맵핑정보 생성
        controllerMap.put("/front-controller/v4/members/new-form", new MemberFormControllerV4());
        controllerMap.put("/front-controller/v4/members/save", new MemberSaveControllerV4());
        controllerMap.put("/front-controller/v4/members", new MemberListControllerV4());
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("FrontControllerServletV4.service");

        String requestURI = req.getRequestURI();

        ControllerV4 controller = controllerMap.get(requestURI);

        if (controller == null) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        // model 및 paramMap 생성
        Map<String, String> paramMap = createParamMap(req);
        Map<String, Object> model = new HashMap<>(); // 추가 코드

        String viewName = controller.process(paramMap, model);
        MyView view = viewResolver(viewName);
        
        view.render(model, req,resp); // render 호출
        // 프론트컨트롤러가 직접 컨트롤하기에  model을 modelView 객체 에서 안꺼낸다.

        /* v3 코드
         ModelView mv = controller.process(paramMap);
        // v3 에서 modelView에서 받아오던 뷰네임이 필요 없어짐 -> v4
        String viewName = mv.getViewName(); //  논리이름 new-form
        MyView view = viewResolver(viewName);
       //  model을 modelView 객체 에서 꺼내왔었음.
        view.render(mv.getModel(), req,resp); // render 호출
        */
    }

    private MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp"); // /WEB-INF/vies/new-form.js
    }

    private Map<String, String> createParamMap(HttpServletRequest req) {
        Map<String, String> paramMap = new HashMap<>(); // paramMap 생성 (paramMap에 데이터 담기)
        req.getParameterNames().asIterator().   // 모든 파라미터이름을 조회, 갖고옴
                forEachRemaining(paramName -> paramMap.put(paramName,req.getParameter(paramName)));
                // 키 paramName : 값 req.getParameter(paramName)) -> 모든 파라미터 값 조회
        return paramMap;
    }
}
