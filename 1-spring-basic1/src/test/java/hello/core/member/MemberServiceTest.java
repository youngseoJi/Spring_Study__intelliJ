package hello.core.member;

import hello.core.AppConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
// 테스트
public class MemberServiceTest {

    MemberService memberService;
    @BeforeEach // 테스트 실행전 무조건 실행
    public void beforeEach() {
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
    }
    //    MemberService memberService = new MemberServiceImpl();

    @Test // 회원가입 테스트
    void join() {

        //given
        Member member = new Member(1L, "memberA", Grade.vip);

        //when memberService 가입했을때
        memberService.join(member);
        //when
        Member findMember = memberService.findMember(1L);

        //then
        Assertions.assertThat(member).isEqualTo(findMember);
    }
}
