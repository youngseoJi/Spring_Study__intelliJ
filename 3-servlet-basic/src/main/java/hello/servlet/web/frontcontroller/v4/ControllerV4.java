package hello.servlet.web.frontcontroller.v4;

import java.util.Map;

// 인터페이스
// 로직 동일화 : 필수, 반복되는 코드 등
public interface ControllerV4 {
    /**
     * 미리 model, parmMap 생성해둠. 껍데기 상태
     * @param paramMap
     * @param model
     * @return
     */

    String process(Map<String,String> paramMap,Map<String, Object> model);     // model : <key 값 String : value 값 Object 형> 이 형식으로 맵핑하는 것
}
