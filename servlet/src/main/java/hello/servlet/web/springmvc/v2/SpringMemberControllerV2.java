package hello.servlet.web.springmvc.v2;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

// 컨트롤러 통합하기 - 회원가입, 회원저장, 회원리스트 컨트롤러

@Controller
// 클래스 레벨에 다음과 같이 @RequestMapping 을 두면 메서드 레벨과 조합이 된다
// 디폴트 url 지정가능 + 각 url
@RequestMapping("/springmvc/v2/members")
public class SpringMemberControllerV2 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    // @RequestMapping : 요청 정보를 매핑한다. 해당 URL이 호출되면 이 메서드가 호출된다.
    @RequestMapping("/new-form")
    public ModelAndView newFrom() {
        return new ModelAndView("new-form");
    }

    @RequestMapping("/save")
    public ModelAndView members(HttpServletRequest req, HttpServletResponse resq) {
        String username = req.getParameter("username");
        int age = Integer.parseInt(req.getParameter("age"));
        Member member = new Member(username, age);
        System.out.println("member = " + member);
        memberRepository.save(member);
        ModelAndView mv = new ModelAndView("save-result");
        mv.addObject("member", member);// 이전 코드 mv.getModel().put("member", member);

        return mv;
    }

    @RequestMapping
    public ModelAndView save() {
        List<Member> members = memberRepository.findAll(); // 모든 회원 members 배열에 저장

        ModelAndView mv = new ModelAndView("members");
        mv.addObject("members", members); // mv.getModel().put("members", members);

        return mv;
    }
}