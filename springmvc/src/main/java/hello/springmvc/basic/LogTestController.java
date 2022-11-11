package hello.springmvc.basic;

// 로그

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// @RestController : http메세지에 반환 값을 그대로 넣어준다. controller은 view이름은 반환, 통해서 출력
@RestController
public class LogTestController {
    private final Logger log = LoggerFactory.getLogger(LogTestController.class); // 클래스 지정
//  private final Logger Log = LoggerFactory.getLogger(getClass());

    @RequestMapping("/log-test")
    public String Logtest() {
        String name = "Spring";

        System.out.println("name = " + name); 
        // 시스템 콘솔로 직접 출력방식은 로그방식과 달리 무조건 출력하기에, 운영서버에서 온갖 로고가 찍힐 수 있어서 실무에서 사용X
        

        /** 로그를 찍을때 각 레벨 존재 : TRACE > DEBUG > INFO > WARN > ERROR > FATAL 순
         * properties에 로그 레벨을 지정하여, 출력될 로그를 선택할 수 있다.
         * ex) DEBUG 를 설정할시, trace 단계를 제외한 모든 로그가 출력 -> 이때 운영서버에서 필요없는 온갖로고가 찍힐 수 있다!
         * -> 배포할 경우엔 INFO로 변경해야함!
         *
         * 로그 방식 반환 예시 :  2022-11-11 10:51:29.014  INFO 26252 --- [nio-8080-exec-1] hello.springmvc.basic.LogTestController  : info log=Spring
         * 많은 정보와 해당 클래스 위치를 포함하여 버그 찾기 수월
         *  */

        // 1. 추적, 개발서버에서 사용
        log.trace("trace log={}", name);
        //log.trace("trace log=" + name); //연산만 할 뿐, 로그 출력 X - 로그는 이방식을 쓰지 X
        // 2. 디버그용, 개발서버에서 사용
        log.debug("debug log={}", name);
        // 3. 중요한 정보, 운영시스템에서만 보여야할 중요한 정보 등..
        log.info("info log={}", name);
        //log.info("info log="+ name); // info만 로그 출력 함
        // 4. 경고
        log.warn("warn log={}", name);
        // 5. 에러 발생
        log.error("error log={}", name);

        return "ok";
    }


}
