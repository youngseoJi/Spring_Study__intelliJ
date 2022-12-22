package hello.servlet.web.frontcontroller.v2;

import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v2.Controller.MemberFormControllerV2;
import hello.servlet.web.frontcontroller.v2.Controller.MemberListControllerV2;
import hello.servlet.web.frontcontroller.v2.Controller.MemberSaveControllerV2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

// frontController v2
// 공통로직 처리 - view 분리 (화면을 랜더링 하기 위핸 MyView객체 생성)


@WebServlet(name = "frontControllerServletV2", urlPatterns = "/front-controller/v2/*")

public class FrontControllerServletV2 extends HttpServlet {

    // url 매핑 정보 생성
    // String이 들어오면 ControllerV2로 찾는다.
    private Map<String, ControllerV2> controllerMap = new HashMap<>();

    public FrontControllerServletV2() {
        // 특정 "url" 이 호출되면 , 해당 컨트롤러 호출 -> 실행 -> controllerMap에 값을 담아준다. -> 맵핑정보 생성
        controllerMap.put("/front-controller/v2/members/new-form", new MemberFormControllerV2());
        controllerMap.put("/front-controller/v2/members/save", new MemberSaveControllerV2());
        controllerMap.put("/front-controller/v2/members", new MemberListControllerV2());
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("FrontControllerServletV2.service");

        // 호출된 해당 url 반환 : /front-controller/v2/members
        String requestURI = req.getRequestURI();

        // url에 해당되는 컨트롤러 호출, 인스턴스를 반환 : MemberListControllerV2()
        ControllerV2 controller = controllerMap.get(requestURI);

        // 에러처리 컨트롤러가 없다면? 404반환
        if (controller == null) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        // 조회가 성공할 경우 ? 화면 랜더링
        MyView view = controller.process(req, resp); // process 호출 -> new MyView 생성하여 리턴함
        // == new MyView("/WEB-INF/views/new-form.jsp"); 동일한것

        view.render(req,resp); // render 호출
    }
}
