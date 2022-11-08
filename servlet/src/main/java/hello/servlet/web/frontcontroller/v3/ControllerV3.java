package hello.servlet.web.frontcontroller.v3;
import hello.servlet.web.frontcontroller.ModelView;
import java.util.Map;

// 서블릿 종속성 제거
public interface ControllerV3 {
    // 요청 파라미터 정보 Map으로 넘기기 (컨트롤러가 서블릿 기술 없이 동작한다.)
    ModelView process(Map<String, String> paramMap);
}
