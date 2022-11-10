package hello.servlet.web.springmvc.v3;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/springmvc/v3/members")
public class SpringMemberControllerV3 {

    private MemberRepository memberRepository = MemberRepository.getInstance();
    // @RequestMapping 기능 : url 매칭하기, HTTP Method 구분하기
    // @RequestMapping(value = "/new-form", method = RequestMethod.GET)
    @GetMapping("/new-form")
    public String newFrom() {
        return "new-form";
        // ModelAndView로 반환을 하지않아도 알아서, dp노테이션이 view이름을 반환했음을 알고 프로세스가 진행된다.
    }
    /*  public ModelAndView newFrom() {
        return new ModelAndView("new-form"); */

//    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @PostMapping("save")
    public String save(
            // 요청 파라미터 명과, 타입캐스팅도 처리 가능(타로 int 파싱 안해줘도 된다.)
            @RequestParam("username") String username,
            @RequestParam("age") int age,
            Model model) {

        Member member = new Member(username, age);;
        memberRepository.save(member);

        // model에 가입한 회원정보 담아주기
        model.addAttribute("member", member);
        return "save-result"; // view 이름 반환
    }

//    @RequestMapping(method = RequestMethod.GET)
    @GetMapping
    public String members(Model model) {
        List<Member> members = memberRepository.findAll(); // 모든 회원 members 배열에 저장

        model.addAttribute("members", members);
        return "members";
    }
}
