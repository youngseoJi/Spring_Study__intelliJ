package hello.servlet.web.springmvc.old;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 스프링 mvc controller 과거버전  ( 스프링 mvc 컨트롤러 이노테이션과 완전히 다른것 !!!!)
@Component("/springmvc/old-controller") // 컴포넌트 스캔 : "지정한 이름"으로 스캔한 것을 스프링 빈으로 등록한다.- 빈의 이름으로 핸들러를 찾도록
public class OldController implements Controller {

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("OldController.handleRequest"); // controller 호출성공 확인
        return new ModelAndView("new-form");

    }
}
