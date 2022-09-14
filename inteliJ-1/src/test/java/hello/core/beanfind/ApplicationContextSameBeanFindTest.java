package hello.core.beanfind;

import hello.core.discount.DiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ApplicationContextSameBeanFindTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SameBeanConfig.class);

    @Test
    @DisplayName("타입으로 조회시 같은 타입이 둘 이상일경우, 중복 오류 발생")
    void findBeanByTypeDuplicate(){
        // 스피링빈 조회 : 타입만 지정 (MemberRepository의 클래스만 조회)
        // MemberRepository bean = ac.getBean(MemberRepository.class); // 타입만 지정
        assertThrows(NoUniqueBeanDefinitionException.class, () ->
                ac.getBean(MemberRepository.class));
    }

    @Test
    @DisplayName("타입으로 조회시 같은 타입이 둘 이상 있으면, 빈 이름을 지정하면 된다")
    void findBeanByName() {

        MemberRepository memberRepository = ac.getBean("memberRepository1", MemberRepository.class);
        // 테스트 검증  - memberRepository1 이 MemberRepository 타입인지
        assertThat(memberRepository).isInstanceOf(MemberRepository.class);
    }

    @Test
    @DisplayName("특정 타입을 모두 조회하기")
    void findAllBeanByType() {
        // 키 , 값
        Map<String, MemberRepository> beansOfType = ac.getBeansOfType(MemberRepository.class);
        for (String key : beansOfType.keySet()) {
            System.out.println("key = " + key + "value = " + beansOfType.get(key));
        }

        System.out.println("beansOfType = " + beansOfType);
        // beansOfType : 스프링빈타입
        // 테스트 스프링빈타입 사이즈(개수)기 2(2개)여야한다.
        assertThat(beansOfType.size()).isEqualTo(2);

    }
    @Configuration
    static class SameBeanConfig {
        /**같은 타입이 둘 이상일경우 */
        @Bean
        public MemberRepository memberRepository1() { // 이름
            return new MemoryMemberRepository(); // 인스턴스, 클래스 타입
        }
        @Bean
        public MemberRepository memberRepository2() {
            return new MemoryMemberRepository();
        }

    }
}
