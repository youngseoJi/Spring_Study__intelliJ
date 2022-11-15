package hello.springmvc.basic.response;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


//뷰 템플릿을 호출하는 컨트롤러
@Controller
public class ResponseViewController {

    @RequestMapping("/response-view-v1")
    public ModelAndView responseViewV1() {
        ModelAndView mav = new ModelAndView("response/hello")
                .addObject("data", "hello");

        return mav;
    }

    // 권장하는 방법
    // String을 반환하는 경우
    /** @ResponseBody 가 없으면? 뷰의 논리이름인 response/hello 반환 ->  뷰 리졸버가 실행되어서 뷰를 찾고, 렌더링 함.
     * @ResponseBody 가 있으면? 뷰 리졸버를 실행하지 않고, HTTP 메시지 바디에 직접 "response/hello" 라는 문자가 출력됨.
     */
    @RequestMapping("/response-view-v2")
    public String responseViewV2(Model model) {
        model.addAttribute("data","hello~~~");

        return "response/hello";
    }

    // Void를 반환하는 경우 / 권장 X
    // @Controller 경로의 이름과 뷰의 논리적이름이 동일하고 아무것도 반환하지 않는 경우
    @RequestMapping("/response/hello")
    public void responseViewV3(Model model) {
        model.addAttribute("data", "hello!!");
    }

}
