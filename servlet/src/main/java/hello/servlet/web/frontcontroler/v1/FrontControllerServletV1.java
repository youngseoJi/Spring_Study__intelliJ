package hello.servlet.web.frontcontroler.v1;

import hello.servlet.web.frontcontroler.v1.Controller.MemberListControllerV1;
import hello.servlet.web.frontcontroler.v1.Controller.MemberSaveControllerV1;
import hello.servlet.web.frontcontroler.v1.Controller.MemberformControlerV1;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

// frontController 도입 v1
// - url 매핑 정보에서 컨트롤러 조회 -> 컨트롤러 호출 -> 컨트롤러에서 JSP forward

// urlPatterns : * 어떤 url이 들어와도 서블릿이 호출된다.
@WebServlet(name = "frontControllerServletV1", urlPatterns = "/front-controller/v1/*")
//  ( 프론트 컨트롤러만 서블릿 상속 )
public class FrontControllerServletV1 extends HttpServlet {

    // url 매핑 정보 생성
    private Map<String, ControllerV1> controllerMap = new HashMap<>();
    // 생성자
    public FrontControllerServletV1() {
        // 특정 "url" 이 호출되면 , 해당 컨트롤러 호출 -> 실행 -> controllerMap에 값을 담아준다. -> 맵핑정보 생성
        controllerMap.put("/front-controller/v1/members/new-form", new MemberformControlerV1());
        controllerMap.put("/front-controller/v1/members/save", new MemberSaveControllerV1());
        controllerMap.put("/front-controller/v1/members", new MemberListControllerV1());
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("FrontControllerServletV1.service");

        // 호출된 해당 url 반환 : /front-controller/v1/members
        String requestURI = req.getRequestURI();
        // url에 해당되는 인스턴스를 반환 : MemberformControlerV1()
        ControllerV1 controller = controllerMap.get(requestURI);

        // 에러처리 컨트롤러가 없다면? 404반환
        if (controller == null) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        // 조회가 성공할 경우?
        controller.process(req, resp);
    }
}
