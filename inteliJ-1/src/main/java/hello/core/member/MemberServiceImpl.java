package hello.core.member;
// MemverService 기능에 대한 구현체 생성
public class MemberServiceImpl implements MemberService {
    // ctrl + shift + Enter 자동완성
    private final MemberRepository memberRepository = new MemoryMemberRepository();
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
}


// @Override 어노테이션의 기능
//자식 클래스에 여러 개의 메서드가 정의가 되어 있을 경우
//해당 메소드가 부모 클래스에 있는 메서드를 Override 했다는 것을 명시적으로 선언합니다.