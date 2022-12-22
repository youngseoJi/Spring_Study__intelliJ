package hello.core.member;

// 회원 서비스 기능 두개
public interface MemberService {
    // 회원가입
    void join(Member member);

    Member findMember(Long memberId);
}
