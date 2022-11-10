package hello.servlet.web.frontcontroller.v5;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v3.ControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;
import hello.servlet.web.frontcontroller.v4.controller.MemberFormControllerV4;
import hello.servlet.web.frontcontroller.v4.controller.MemberListControllerV4;
import hello.servlet.web.frontcontroller.v4.controller.MemberSaveControllerV4;
import hello.servlet.web.frontcontroller.v5.adapter.ControllerV3HandlerAdapter;
import hello.servlet.web.frontcontroller.v5.adapter.ControllerV4HandlerAdapter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "frontControllerServletV5", urlPatterns = "/front-controller/v5/*")
public class FrontControllerServletV5 extends HttpServlet {
    // 어댑터 방식 " Object 모든 컨트롤러 인스턴스 타입을 지원한다. 고정 X
    private final Map<String, Object> handlerMappingMap = new HashMap<>();
/* v3 기존 코드 : 특정 타입만 지원했었다. Controllerv3
 *  private Map<String, Controllerv3> handlerMappingMap = new HashMap<>();
*/
    private final List<MyHandlerAdapter> handlerAdapters = new ArrayList<>();

    public FrontControllerServletV5() {
        initHandlerMappingMap();

        //  handlerAdapters 리스트 : ControllerV3 컨트롤러 인스턴스를 지원하는 어댑터(컨트롤러) 를 생성 -> 추가
        initHandlerAdapters();
    }

    // 핸들러 맵핑 정보
    private void initHandlerMappingMap() {
        // v3 핸들러 추가
        handlerMappingMap.put("/front-controller/v5/v3/members/new-form", new MemberFormControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/members/save", new MemberSaveControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/members", new MemberListControllerV3());
        // v4 핸들러 추가
        handlerMappingMap.put("/front-controller/v5/v4/members/new-form", new MemberFormControllerV4());
        handlerMappingMap.put("/front-controller/v5/v4/members/save", new MemberSaveControllerV4());
        handlerMappingMap.put("/front-controller/v5/v4/members", new MemberListControllerV4());
    }

    // 핸들러 어댑터
    private void initHandlerAdapters() {
        // v3 핸들러 지원하는 어댑터
        handlerAdapters.add(new ControllerV3HandlerAdapter());
        // v4 핸들러 지원하는 어댑터
        handlerAdapters.add(new ControllerV4HandlerAdapter());
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 1. 핸들러 호출 (조회): Object 타입 handler(컨트롤러) 반환 = 요청정보를 갖고 핸들러를 찾는 함수 호출
        Object handler = getHandler(req);
        // 반환 MemberFormControllerV3 / MemberFormControllerV4

        // 에러처리
        if (handler == null) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND); 
            return;
        }

        // HandlerAdapter 호출 : handelr(컨트롤러)를 지원하는 어댑터 호출
        MyHandlerAdapter adapter = getHandlerAdapter(handler);
        // 반환 ControllerV3HandlerAdapter / ControllerV4HandlerAdapter

        // ModelView 반환 
        ModelView mv = adapter.handle(req,resp,handler);

        String viewName = mv.getViewName(); //  논리이름 new-form
        // viewResolver 호출
        MyView view = viewResolver(viewName);
        // view  render 호출 -> model 넘겨주기
        view.render(mv.getModel(), req,resp);
    }


    // 핸들러 어댑터 조회(핸들러를 지원하는 인터페이스)
    private MyHandlerAdapter getHandlerAdapter(Object handler) {

        // handlerAdapters (핸들러(컨트롤러) 리스트)를 모두 조회함 -  [ MemberFormControllerV3 , MemberFormControllerV4]
        for (MyHandlerAdapter adapter : handlerAdapters) {
            // adapter(컨트롤러)가 .supports(handelr/컨트롤러)를 지원하는지 true/false로 체크
            if (adapter.supports(handler)) {
                return adapter;
                // 해당 adapter(컨트롤러)를 반환 : MemberFormControllerV3 / MemberFormControllerV4
            }
        }
        // 모든 adapter가 handelr을 지원하지 않으면?
        // 에러처리 :  IllegalArgumentException 아규먼트가 잘못되었을 경우
        throw new IllegalArgumentException("Handler adapter 를 찾을 수 없다. handler =" + handler);
                                            // 디버깅용 : 어떤 핸들러의 어댑터가 없는지 체크되도록
    }

    // 핸들러(지원하는 컨트롤러) 조회: 매핑정보를 갖고 핸들러 맵에서 찾기
    private Object getHandler(HttpServletRequest req) {
        // 해당 url 갖고오기
        String requestURI = req.getRequestURI();

        // handlerMappingMap에서 url에 해당되는 컨트롤러 호출, 인스턴스를 반환 -> 해당 컨트롤러/핸들러

        return handlerMappingMap.get(requestURI);

    }

    private MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp"); // /WEB-INF/vies/new-form.js
    }
}

