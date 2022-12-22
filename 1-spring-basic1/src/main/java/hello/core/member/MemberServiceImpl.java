package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

// MemverService 기능에 대한 구현체 생성
@Component
public class MemberServiceImpl implements MemberService {
    // ctrl + shift + Enter 자동완성
    private final MemberRepository memberRepository;

    @Autowired // 의존관계 자동 주입 // ac.getBean(MemberRepository.class) 처럼 동작한다.
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    //  회원가입
    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }
   // 회원조회
    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    // 테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}


// @Override 어노테이션의 기능
//자식 클래스에 여러 개의 메서드가 정의가 되어 있을 경우
//해당 메소드가 부모 클래스에 있는 메서드를 Override 했다는 것을 명시적으로 선언합니다.