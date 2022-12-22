package hello.servlet.domain.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 동시성 문제가 고려되어 있지 않음, 실무에서는 ConcurrentHashMap, AtomicLong 사용 고려
 */

public class MemberRepository {
//    static 이여서 new MemberRepository 여러개여도, 1개만 생성된다. - 싱글톤이여서 없어도 됨 이미 보장되기에

    // 키 id, 값 Member
    private static Map<Long, Member> store = new HashMap<>();
    // id가 하나씩 증가하도록
    private static long sequence = 0L;
    
    // 싱글톤 : private로 아무나 생성하지 못하도록 막아야함
    private static final MemberRepository instance = new MemberRepository();
    private MemberRepository() {
    }

    // getInstance() 로만 조회할 수 있음! 같은 클래스 내에 있는 정의되어있는 함수로만 private로 생성된 생성자 사용?조회 가능
    public static MemberRepository getInstance() {
        return instance;
    }


    // 멤버 가입
    public Member save(Member member) {
        member.setId(++sequence); // 멤버 저장될때마다 +1 멤버 아이디
        store.put(member.getId(), member); // +1 된 멤버 아이디, 해당 멤버를 회원가입
        return member;
    }

    // 멤버 조회 - id로 조회
    public Member findById(Long id) {
        return store.get(id);
    }

    // 모든 멤버 조회
    public List<Member> findAll() {
        // store의 모든 값을 .values 꺼내서 배열에 담아주기 - 모든 멤버 조회
        return  new ArrayList<>(store.values());
    }

    // 모든 멤버 회원 탈퇴
    public void  clearStore() {
        store.clear();
    } 
}
