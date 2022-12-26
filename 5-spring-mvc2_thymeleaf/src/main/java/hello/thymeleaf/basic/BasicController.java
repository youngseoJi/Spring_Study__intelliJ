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

    // operation 연산
    @GetMapping("/operation")
    public String operation(Model model){
        model.addAttribute("nullData", null);
        model.addAttribute("data", "spring~");
        return "/basic/operation";

    }


    // 속성값 설정
    @GetMapping("/attribute")
    public String attribute() {
        return "basic/attribute";
    }

    // each 반복
    @GetMapping("/each")
    public String each(Model model) {
        // addUsers : 회원 저장 기능 사용
        addUsers(model);
        return "basic/each";
    }

    // 회원 저장 기능
    // list에 회원을 담은 model 저장소
    private void addUsers(Model model) {
        List<User> list = new ArrayList<>();
        list.add(new User("userA", 10));
        list.add(new User("userB", 20));
        list.add(new User("userC", 30));

        // model 저장소에 user 키 : list {..,..,..} 값을 담는다.
        model.addAttribute("users", list);
    }

    // 조건문
    @GetMapping("/condition")
    public String condition(Model model) {
        addUsers(model);
        return "basic/condition";
    }

    // 타임리프 주석

    @GetMapping("/comments")
    public String comments(Model model) {
        model.addAttribute("data", "Spring");
        return "basic/comments";
    }

    // 블록 block : 타임리프 유일한 자체 태그

    @GetMapping("/block")
    public String block(Model model) {
        addUsers(model);
        return "basic/block";
    }

    // 자바스크립트인라인 : 자바스크립트에서 타임리프를 편리하게 사용할 수 있는 기능
    @GetMapping("/javascript")
    public String javascript(Model model) {

        model.addAttribute("user1", new User("estell", 25));
        model.addAttribute("user2", new User("estell\"A\"", 30));
        addUsers(model);
        return "basic/javascript";
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
