package hello.servlet.web.frontcontroller.v5.adapter;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.v3.ControllerV3;
import hello.servlet.web.frontcontroller.v5.MyHandlerAdapter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

// 컨트롤러 V3를 지원하는 컨트롤러(어댑터)
public class ControllerV3HandlerAdapter implements MyHandlerAdapter {

    @Override
    public boolean supports(Object handler) {
        /* ControllerV3만 지원하는 어댑터
         ControllerV3 인터페이스로 구현한 것이 넘어오면 true를 반환, 다른 인터페이스로 구현한 것이 넘어오면 false 를 반환한다.
        */
        // handler : MemberFormControllerV3
        return (handler instanceof ControllerV3);
        // 반환값 : true -> ControllerV3인스턴스를 사용하기에

    }

    // 어댑터의 역할 : 핸들러를 호출해주고, 결과가 오면 반환타입을 ModelView 타입으로 맞춰서 반환해줘야한다.
    @Override
    public ModelView handle(HttpServletRequest req, HttpServletResponse res, Object handler) throws ServletException, IOException {
        // controller : MemberFormControllerV3
        ControllerV3 controller = (ControllerV3) handler;  // Object타입 ControllerV3 타입으로 캐스팅, 타입변환
        Map<String, String> paramMap = createParamMap(req);
        ModelView mv = controller.process(paramMap); // ModelView 반환
        return mv;
    }

    private Map<String, String> createParamMap(HttpServletRequest req) {
        Map<String, String> paramMap = new HashMap<>(); // paramMap 생성 (paramMap에 데이터 담기)
        req.getParameterNames().asIterator().   // 모든 파라미터이름을 조회, 갖고옴
                forEachRemaining(paramName -> paramMap.put(paramName,req.getParameter(paramName)));
        // 키 paramName : 값 req.getParameter(paramName)) -> 모든 파라미터 값 조회
        return paramMap;
    }
}
