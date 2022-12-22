package hello.servlet.domain;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class MemberRepositoryTest {
    
    // 싱글톤이여서 new X , 이미 생성된 인스턴스 객체를 불러옴
    MemberRepository memberRepository = MemberRepository.getInstance();

    @AfterEach // 테스트 초기화 - 무조건 테스트 1개가 끝날떄 마다 실행됨
    void  afterEach() {
        // clearStore(); : 초기화 필수! 값이 쌓여서 다음 테스트에서 오류발생
        memberRepository.clearStore();
    }

    @Test

    // 회원가입 테스트
    void  save() {
        //given : 어떠한 거, 상황 주어졌을떄
        Member member = new Member("영서", 29);

        //when : 이런거 실행했을때
        Member savedMember = memberRepository.save(member);

        //then : 결과가 이거여야함
        Member findMember = memberRepository.findById(savedMember.getId()); // 가입한 회원의 id 조회 -> 해당 id로 저장소에서 회원조회
        // 가입된 회원과 저장소의 회원의 값이 동일해야함
        assertThat(findMember).isEqualTo(savedMember);
    }

    // 모든 회원 조회
    @Test
    void findAll() {
        //given 회원 생성
        Member member1 = new Member("member1", 10);
        Member member2 = new Member("member2", 20);

        // 저장소에 회원 저장
        memberRepository.save(member1);
        memberRepository.save(member2);

        //when
        List<Member> allMember = memberRepository.findAll();

        //then
        // 회원 수가 2랑 같아야한다.
        assertThat(allMember.size()).isEqualTo(2);
        // 회원에 회원1,2가 포함되어 있어야한다.
        assertThat(allMember).contains(member1,member2);

    }
}
