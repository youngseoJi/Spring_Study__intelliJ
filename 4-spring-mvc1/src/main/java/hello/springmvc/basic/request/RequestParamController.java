package hello.springmvc.basic.request;

import hello.springmvc.basic.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;


@Slf4j // log
@Controller
public class RequestParamController {

    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));

        // log
        log.info("username={}, age={}", username, age);

        response.getWriter().write("ok");
    }
    // 편리하게 http파라미터 요청

    // @RequestParam : 파라미터 이름으로 바인딩
    // @ResponseBody : View 조회를 무시하고, HTTP message body에 직접 해당 내용 입력
    @ResponseBody
    @RequestMapping("/request-param-v2")
    public String requestParamV2(
            @RequestParam("username") String username,
            @RequestParam("age") int age) {
        log.info("username={}, age={}", username, age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-v3")
    public String requestParamV3(
            //HTTP 파라미터 명이 변수 명과 동일 할 경우, 생략가능
            @RequestParam String username,
            @RequestParam int age) {
        log.info("username={}, age={}", username, age);
        return "ok";
    }


    // @RequestParam 생략 가능 : String , int , Integer 등의 단순 타입일 경우
    // 생략할경우 -> 스프링 MVC는 내부에서 required=false 를 적용한다.
    // but 쓰는 것이 더 명확하다고 생각한다.
    @ResponseBody
    @RequestMapping("/request-param-v4")
    public String requestParamV4( String username, int age) {
        log.info("username={}, age={}", username, age);
        return "ok";
    }
    
    // @RequestParam.required: 파라미터 필수 여부 설정
    // 필수 : required = true (기본값)  / 필수 X : required = false
    // localhost:8080/request-param-required?username= 할경우 빈문자로 검색한 것으로 인식
    @ResponseBody
    @RequestMapping("/request-param-required")
    public String requestParamRequired(
            @RequestParam(required = true) String username,
            @RequestParam(required = false) Integer age) {
        // age는 필수값 아니기에, 값이 없는 경우인 기본값 null 셋팅필요    -> 출력 username=hello, age=null
        // int age 는 X int는 기본형이여서, null일 수 없다. 객체형인 Integer로 변경함
        log.info("username={}, age={}", username, age);
        return "ok";
    }

    // defaultValue : 파라미터에 값이 없는 경우 기본 값을 적용할 수 있다.
    // required 필요 X ( 이미 기본 값이 설정되어있기에 )
    // required 다른점 : username= 할경우 빈문자도 값이 없는경우로 판단함
    @ResponseBody
    @RequestMapping("/request-param-default")
    public String requestParamDefault(
            @RequestParam(defaultValue = "guest") String username,
            @RequestParam(defaultValue = "-1") int age) {
        log.info("username={}, age={}", username, age);
        //username=guest, age=-1
        return "ok";
    }

    //파라미터를 Map으로 조회하기 - {key : value, ...}
    @ResponseBody
    @RequestMapping("/request-param-map")
    public String requestParamMap(
            @RequestParam Map<String, Object> paramMap) {
        // 매핑 정보에서 .get 으로 파라미터 조회
        log.info("username={}, age={}", paramMap.get("username"),  paramMap.get("age"));
        //username=guest, age=-1
        return "ok";
    }

    // @ModelAttribute 적용
    /* 스프링 mvc에 적용할 경우
     * HelloData 객체 생성
     * 요청 파라미터의 이름으로 HelloData 객체의 프로퍼티를 찾음
     * ->  해당 프로퍼티의 setter를 호출해서 파라미터의 값을 입력(바인딩)
        예) 파라미터 이름이 username 이면 setUsername() 메서드를 찾아서 호출하면서 값을 입력
    */
    @ResponseBody
    @RequestMapping("/model-attribute-v1")
    public String modelAttributeV1(@ModelAttribute HelloData helloData){

        // helloData 인스턴스에서 값 조회
        log.info("username={}, age={}", helloData.getUsername(),  helloData.getAge());
        return "ok";
    }
   /* 이 코드가 단축되는 것
    public String modelAttributeV1(@RequestParam String username, @RequestParam int age){
        HelloData helloData = new HelloData();
        helloData.setUsername(username);
        helloData.setAge(age);

        log.info("username={}, age={}", helloData.getUsername(),  helloData.getAge());
        return "ok";
    }
    */
    
    // @ModelAttribute 생략가능 - 혼란생길 수 있음  @RequestParam도 생략되기때문에
   @ResponseBody
   @RequestMapping("/model-attribute-v2")
   public String modelAttributeV2(HelloData helloData){

       log.info("username={}, age={}", helloData.getUsername(),  helloData.getAge());
       return "ok";
   }

}
