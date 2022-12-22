package hello.thymeleaf.basic;

import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/basic")
public class BasicController {

    // text 텍스트
    @GetMapping("text-basic")
    public String textBasic(Model model) {
        model.addAttribute("data", "Hello <b>spring</b>");
        return "basic/text-basic";
    }

    @GetMapping("text-unescaped")
    public String textUnescaped(Model model) {
        model.addAttribute("data", "Hello <b>spring</b>");
        return "basic/text-unescaped";
    }

    // variable 변수
    @GetMapping("/variable")
    public String variable(Model model) {
        // user 회원 객체/인스턴스 생성
        User userA = new User("userA", 10);
        User userB = new User("userB", 20);

        // 배열/리스트에 회원 담기
        List<User> list = new ArrayList<>();
        list.add(userA);
        list.add(userB);

        // {키 : 값} 형태로 매핑
        Map<String, User> map = new HashMap<>();
        map.put("userA", userA); // {userA : userA}
        map.put("userB", userB);

        // model 저장소에 담기 
        model.addAttribute("user", userA);
        model.addAttribute("users", list);
        model.addAttribute("userMap", map);

        // 뷰 리렌더링
        return "basic/variable";
    }

    // 객체 Objects
    @GetMapping("/basic-objects")

    // HttpSession : 유저가 웹브라우져를 끄지않는이상 데이터가 날라가지않고 유지된다.( httpRequest는 요청과 응답하고 데이터 날라감)
    public String basicObjects(HttpSession session) {
        session.setAttribute("sessionData", "Hello Session");
        return "basic/basic-objects";
    }
    // 스프링빈 생성 (빈 이름 : helloBean)
    @Component("helloBean")
    static class HelloBean {
        public String hello(String data) {
            return "Hello " + data;
        }
    };

    // 타임리프 유틸리티 객체 : 자바8 날짜
    @GetMapping("/date")
    public String date(Model model) {
        model.addAttribute("localDateTime", LocalDateTime.now());
        return  "basic/date";
    }

    // URL 링크

    @GetMapping("/link")
    public String link(Model model) {
        model.addAttribute("param1", "data1");
        model.addAttribute("param2", "data2");
        return "basic/link";
    }

    // literal 리터럴 (문자) '' 작은 따옴표 필수!
    @GetMapping("/literal")
    public String literal(Model model){
        model.addAttribute("data", "Spring");
        return "basic/literal";
    }

    @Data
    static class User {
        private String username;
        private int age;

        public User(String username, int age) {
            this.username = username;
            this.age = age;

        }
    }
}
