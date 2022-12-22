package hello.springmvc.basic.request;

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
import java.io.InputStream;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

@Slf4j
@Controller
public class RequestBodyStringController {


    @PostMapping("/request-body-string-v1")
    public void requestBodyStringV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletInputStream inputStream = request.getInputStream(); // InputStream : HTTP 요청 메시지 바디의 내용 갖고오기
        // 스트림은 byte코드 -> byte 코드를 문자를 받을경우 어떤 인코딩으로해서 문자로 바꿀건지 지정해줘야함
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        log.info("messageBody={}", messageBody);

        response.getWriter().write("ok");
    }
    // 서블릿 코드 제거 -> InputStream , OutputStream 바로 받기
    // InputStream(Reader): HTTP 요청 메시지 바디의 내용을 직접 조회
    // OutputStream(Writer): HTTP 응답 메시지의 바디에 직접 결과 출력

    @PostMapping("/request-body-string-v2")
    public void requestBodyStringV2(InputStream inputStream, Writer responseWriter) throws IOException {

        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        log.info("messageBody={}", messageBody);

        responseWriter.write("ok");
    }

    // HttpEntity : HTTP header, body 정보를 편리하게 조회, 메세지 컨버터 기능 사용
    @PostMapping("/request-body-string-v3")
    public HttpEntity<String> requestBodyStringV3(HttpEntity<String> httpEntity) throws IOException{

        String messageBody = httpEntity.getBody();
        log.info("messageBody={}", messageBody);

        return new HttpEntity<>("ok");
    }


    // 실무에서 많이 사용하는 방법

    // @RequestBody : HTTP 메시지 바디 정보를 편리하게 조회함
    // @ResponseBody : 응답 결과를 HTTP 메시지 바디에 직접 담아서 전달함
    // 참고로 헤더 정보가 필요하다면 HttpEntity or @RequestHeader 사용하면 됌
    @ResponseBody // 응답
    @PostMapping("/request-body-string-v4")       // http 메시지 읽어서 바로 담아줌
    public String requestBodyStringV4(@RequestBody String messageBody) {

        log.info("messageBody={}", messageBody);

        return "ok";
    }
}


/**
 * 요청 파라미터 vs HTTP 메시지 바디
 * 요청 파라미터를 조회하는 기능: @RequestParam , @ModelAttribute
 * HTTP 메시지 바디를 직접 조회하는 기능: @RequestBody
 *  @ResponseBody : 응답 결과를 HTTP 메시지 바디에 직접 담아서 전달함, view를 사용하지 않는다.
 */