package hello.springmvc.basic.requestmapping;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.awt.*;

@RestController
public class MappingController {

    private Logger log = LoggerFactory.getLogger(getClass());

    @RequestMapping(value = "/hello-basic", method = RequestMethod.GET)
   // @RequestMapping(value = {"/hello-basic", "/hello-go"}) url 여러개 설정가능
    public String helloBasic() {
        log.info("helloBasic");
        return "ok";
    }

    /**
     * method 특정 HTTP 메서드 요청만 허용
     * GET, HEAD, POST, PUT, PATCH, DELETE
     */


    @RequestMapping(value = "/mapping-get-v1", method = RequestMethod.GET)
    public String mappingGetV1() {
        log.info("mappingGetV1");
        return "ok";
    }

    /**
     * 편리한 축약 애노테이션 (코드보기)
     * @GetMapping
     * @PostMapping
     * @PutMapping
     * @DeleteMapping
     * @PatchMapping
     */
    @GetMapping(value = "/mapping-get-v2")
    public String mappingGetV2() {
        log.info("mapping-get-v2");
        return "ok";
    }

    /**
     * PathVariable 경로변수 사용
     * 변수명이 같으면 생략 가능
     * @PathVariable("userId") String userId -> @PathVariable userId
     */
    @GetMapping("/mapping/{userId}")
    public String mappingPath(@PathVariable("userId") String data) {
        log.info("mappingPath userId={}", data);
        /* http://localhost:8080/mapping/1
        2022-11-11 15:29:02.639  INFO 58376 --- [nio-8080-exec-7] h.s.b.requestmapping.MappingController   : mappingPath userId=1
        */
        return "ok";
    }

    /**
     * PathVariable 사용 다중
     */
    @GetMapping("/mapping/users/{userId}/orders/{orderId}")
    public String mappingPath(@PathVariable String userId, @PathVariable Long
            orderId) {
        log.info("mappingPath userId={}, orderId={}", userId, orderId);
        return "ok";
    }
    //2022-11-11 15:35:51.470  INFO 67268 --- [nio-8080-exec-5] h.s.b.requestmapping.MappingController   : mappingPath userId=userA, orderId=100

    /**
     * 파라미터로 추가 매핑
     * * 조건 매핑 가능
     * params="mode", // key가 mode일 경우
     * params="!mode" // key가 mode가 아닐경우
     * params="mode!=debug" (! = ) // // key가 mode, value가 debug가 아닌경우
     * params = {"mode=debug","data=good"} // 둘중 하나거나
     */
    @GetMapping(value = "/mapping-param", params = "mode=debug")
    public String mappingParam() {
        log.info("mappingParam");
        return "ok";
    }

    /** 파라미터 매핑과 비슷하지만, HTTP 헤더를 사용한다. : 임의의 헤더값을 넣는다.
     * 특정 헤더로 추가 매핑
     * 조건 매핑 가능
     * headers="mode", // key가 mode일 경우
     * headers="!mode" // key가 mode가 아닐경우
     * headers="mode=debug" // key가 mode, value가 debug
     * headers="mode!=debug" (! = ) // key가 mode, value가 debug가 아닌경우
     */
    @GetMapping(value = "/mapping-header", headers = "mode=debug")
    public String mappingHeader() {
        log.info("mappingHeader");
        return "ok";
    }

    /**
     * Content-Type 헤더 기반 추가 매핑 Media Type
     * consumes : 요청 헤더의 컨텐츠 타입기반으로 매핑
     * consumes="application/json"
     * consumes="!application/json"
     * consumes="application/*"
     * consumes="*\/*"
     * MediaType.APPLICATION_JSON_VALUE
     */
    @PostMapping(value = "/mapping-consume", consumes = MediaType.APPLICATION_JSON_VALUE)
//    @PostMapping(value = "/mapping-consume", consumes = "application/json")
    public String mappingConsumes() {
        log.info("mappingConsumes");
        return "ok";
    }

    /**
     * Accept 헤더 기반 Media Type
     * produces = "text/html"
     * produces = "!text/html"
     * produces = "text/*"
     * produces = "*\/*"
     */
    @PostMapping(value = "/mapping-produce", produces = MediaType.TEXT_HTML_VALUE)
//    @PostMapping(value = "/mapping-produce", produces = "text/html")
    public String mappingProduces() {
        log.info("mappingProduces");
        return "ok";
    }
}
