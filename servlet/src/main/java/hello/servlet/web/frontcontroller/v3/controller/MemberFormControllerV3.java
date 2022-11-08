package hello.servlet.web.frontcontroller.v3.controller;
import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.v3.ControllerV3;
import java.util.Map;
// 컨트롤러는 뷰의 논리 이름을 반환, 실제 물리 위치의 이름은 프론트 컨트롤러에서 처리하도록 단순화
public class MemberFormControllerV3 implements ControllerV3 {

    @Override
    public ModelView process(Map<String, String> paramMap) {
        //뷰 이름 중복 제거 -뷰의 논리 이름을 반환
        // ModelView 를 생성할 때 new-form 이라는 view의 논리적인 이름을 지정
        return new ModelView("new-form");
    }
}
