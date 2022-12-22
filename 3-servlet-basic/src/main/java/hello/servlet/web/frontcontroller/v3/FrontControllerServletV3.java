package hello.servlet.web.frontcontroller.v3;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

// frontController v3
// 공통로직 처리 - view 분리 (화면을 랜더링 하기 위핸 MyView객체 생성)


@WebServlet(name = "frontControllerServletV3", urlPatterns = "/front-controller/v3/*")

public class FrontControllerServletV3 extends HttpServlet {

    // url 매핑 정보 생성
    // String이 들어오면 Controllerv3로 찾는다.
    private Map<String, ControllerV3> controllerMap = new HashMap<>();

    public FrontControllerServletV3() {
        // 특정 "url" 이 호출되면 , 해당 컨트롤러 호출 -> 실행 -> controllerMap에 값을 담아준다. -> 맵핑정보 생성
        controllerMap.put("/front-controller/v3/members/new-form", new MemberFormControllerV3());
        controllerMap.put("/front-controller/v3/members/save", new MemberSaveControllerV3());
        controllerMap.put("/front-controller/v3/members", new MemberListControllerV3());
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("FrontControllerServletV3.service");

        // 호출된 해당 url 반환 : /front-controller/v3/members
        String requestURI = req.getRequestURI();

        // url에 해당되는 컨트롤러 호출, 인스턴스를 반환 : MemberListControllerv3()
        ControllerV3 controller = controllerMap.get(requestURI);

        // 에러처리 컨트롤러가 없다면? 404반환
        if (controller == null) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        Map<String, String> paramMap = createParamMap(req);
        ModelView mv = controller.process(paramMap);

        String viewName = mv.getViewName(); //  논리이름 new-form
        MyView view = viewResolver(viewName);

        view.render(mv.getModel(), req,resp); // render 호출
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
