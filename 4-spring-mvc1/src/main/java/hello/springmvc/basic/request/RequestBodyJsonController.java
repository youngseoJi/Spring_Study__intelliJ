package hello.springmvc.basic.request;


import com.fasterxml.jackson.databind.ObjectMapper;
import hello.springmvc.basic.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * {"username":"hello", "age":20}
 * content-type: application/json
 */
@Slf4j
@Controller
public class RequestBodyJsonController {

    private ObjectMapper objectMapper = new ObjectMapper();

    @PostMapping("/request-body-json-v1")
    // HttpServletRequest : 직접 HTTP 메시지 바디에서 데이터를 읽어오기
    public void requestBodyJsonV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletInputStream inputStream = request.getInputStream();
        // HTTP 메시지 바디에서 데이터 문자로 변환
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        log.info("messageBody={}", messageBody);

        // objectMapper : 문자로 된 JSON 데이터를 자바 객체로 변환
        HelloData helloData = objectMapper.readValue(messageBody, HelloData.class);
        log.info("username={} age={}", helloData.getUsername(), helloData.getAge());

    }
    @ResponseBody
    @PostMapping("/request-body-json-v2")
    // @RequestBody : HTTP 메시지에서 데이터를 꺼냄 -> messageBody에 저장
    public String requestBodyJsonV2(@RequestBody String messageBody) throws IOException {

        log.info("messageBody={}", messageBody);

        // objectMapper :  문자로 된 JSON 데이터인 messageBody 를 자바 객체로 변환
        HelloData helloData = objectMapper.readValue(messageBody, HelloData.class);
        log.info("username={} age={}", helloData.getUsername(), helloData.getAge());
        return "ok";
    }

    /*@RequestBody는 생략 불가능 : HelloData 객체에 @RequestBody 를 생략하면 @ModelAttribute 가 적용되어버림
     *
     * @ModelAttribute, @RequestParam 애노테이션을 생략시
     * String , int , Integer 같은 단순 타입 = @RequestParam
     * 나머지 = @ModelAttribute (argument resolver 로 지정해둔 타입 외)
     *
     * HttpEntity , @RequestBody 사용 시
     * HTTP 메시지 컨버터 : HTTP 메시지 바디의 내용(문자,json)을 지정한 문자나 객체 등으로 변환해줌*/

    @ResponseBody
    @PostMapping("/request-body-json-v3")
    // @RequestBody : 직접 만든 객체를 지정할 수 있음.  -> HelloData
    public String requestBodyJsonV3(@RequestBody HelloData data)  {

        log.info("username={} age={}", data.getUsername(), data.getAge());
        return "ok";
    }

    @ResponseBody
    @PostMapping("/request-body-json-v4")
    public String requestBodyJsonV4(HttpEntity <HelloData> httpEntity)  {

        HelloData data = httpEntity.getBody();
        log.info("username={} age={}", data.getUsername(), data.getAge());
        return "ok";
    }

    /*
     * @RequestBody 요청 : JSON 요청 -> HTTP 메시지 컨버터 ->  객체
     * @ResponseBody 응답 : 객체 -> HTTP 메시지 컨버터 ->JSON 응답*/
    @ResponseBody
    @PostMapping("/request-body-json-v5")
    public HelloData requestBodyJsonV5(@RequestBody HelloData data) {
        log.info("username={}, age={}", data.getUsername(), data.getAge());
        return data; // json 형태로 응답함 -> {"username":"ys", "age":"29"}
    }

}
