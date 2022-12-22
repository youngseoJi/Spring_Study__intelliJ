package hello.springmvc.basic.response;

import hello.springmvc.basic.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@RestController
/** @RestController
 * * HTTP 메시지 바디에 직접 데이터를 입력, Rest API(HTTP API)를 만들 때 사용하는 컨트롤러
 *  @RestController 사용시 해당 컨트롤러에 모두 @ResponseBody 가 적용됨.
 *  -> @RestController =  @Controller + @ResponseBody
 */

public class ResponseBodyController {

    // 서블릿을 직접 다룰 때 처럼
    // HttpServletResponse 객체를 통해서 HTTP 메시지 바디에 직접 ok 응답 메시지를 전달
    @GetMapping("/response-body-string-v1")
    public void responseBodyV1(HttpServletResponse response) throws IOException {
        response.getWriter().write("ok");
    }

    //ResponseEntity (HttpEntity 상속) : HTTP 메시지의 헤더 + 바디 + HTTP 응답 코드(HttpEntity에 X 추가된것)
    @GetMapping("/response-body-string-v2")
    public ResponseEntity<String> responseBodyV2() {
        return new ResponseEntity<>("ok", HttpStatus.OK);
    }

    // ResponseEntity
    @GetMapping("/response-body-string-v3")
    public String responseBodyV3() {
        return "ok";
    }

    // ResponseEntity 반환 : HTTP 메시지 컨버터를 통해서 JSON 형식으로 변환 -> 반환
    // @ResponseBody
    @GetMapping("/response-body-json-v1")
    public ResponseEntity<HelloData> responseBodyJsonV1() {
        HelloData helloData = new HelloData();
        helloData.setUsername("user1");
        helloData.setAge(20);
        return new ResponseEntity<>(helloData, HttpStatus.OK); // http 상태코드 변경가능
    }

    // 많이 쓰는 방식
    // @ResponseStatus : http 상태코드 변경가능하도록 추가
    @ResponseStatus(HttpStatus.OK)
    // @ResponseBody
    @GetMapping("/response-body-json-v2")
    public HelloData responseBodyJsonV2() {
        HelloData helloData = new HelloData();
        helloData.setUsername("user1");
        helloData.setAge(20);
        return helloData;
    }
}
