package hello.servlet.web.springmvc.v1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/* @Controller : 스프링이 자동으로 스프링 빈으로 등록한다. (내부에 @Component 애노테이션이 있어서 컴포넌트 스캔의 대상이 됨)
* RequestMappingHandlerMapping은 @RequestMapping 또는 @Controller가 클래스에 붙어 있는 경우에 매핑 정보로 인식함.
* @Controlle 동일 == @Component & @RequestMapping */
@Controller
public class SpringMemberFormControllerV1 {

    // @RequestMapping : 요청 정보를 매핑한다. 해당 URL이 호출되면 이 메서드가 호출된다.
    @RequestMapping("/springmvc/v1/members/new-form")
    public ModelAndView process() {
        return new ModelAndView("new-form"); // view 이름을 넘겨줘서, 해당 이름의 jsp를 찾아 랜더링함.
    }
}
