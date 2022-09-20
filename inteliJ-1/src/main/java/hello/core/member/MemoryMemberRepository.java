package hello.core.member;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component // 자동 빈이름 : memoryMemberRepository
// @Bean 등록안해도 된다! @ComponentScan 사용했기 때문
public class MemoryMemberRepository implements MemberRepository{
    // 저장소 만들기, Map 필요
    private static Map<Long, Member> store = new HashMap<>();
    // Ctrl + O : 오버라이드 매서드 구현하기

    @Override
    public void save(Member member) {
    // store 저장소에 멤버 저장하기
        store.put(member.getId(), member);
    }

    @Override
    public Member findById(Long memberId) {
        // store 저장소에 저장된 멤머 id로 조회하기
        return store.get(memberId);
    }
}
