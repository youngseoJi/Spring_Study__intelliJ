package hello.servlet.web.frontcontroller.v5.adapter;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.v4.ControllerV4;
import hello.servlet.web.frontcontroller.v5.MyHandlerAdapter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.http.HttpClient;
import java.util.HashMap;
import java.util.Map;


public class ControllerV4HandlerAdapter implements MyHandlerAdapter {
    @Override
    public boolean supports(Object handler) {
        //handler 가 ControllerV4를 지원하는지 true, false 체크
        return (handler instanceof ControllerV4);
        // handler = MemberFormControllerV4 면? true
    }

    @Override
    public ModelView handle(HttpServletRequest req, HttpServletResponse resp, Object handler) throws ServletException, IOException {

        ControllerV4 controller = (ControllerV4) handler;

        Map<String, String> paramMap = createParamMap(req);
        HashMap<String, Object> model = new HashMap<>();

        // viewName 반환
        String viewName = controller.process(paramMap, model);

        // 어댑터 역할! 
        // view 셋팅 : ModelView 생성
        ModelView mv = new ModelView(viewName);
        // view에 model 셋팅
        mv.setModel(model);
        // view 반환
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
