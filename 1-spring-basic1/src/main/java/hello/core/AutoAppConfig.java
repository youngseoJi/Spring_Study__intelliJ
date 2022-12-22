package hello.core;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

// 설정정보
@Configuration
// 컴포넌트 스캔 : @Component 애노테이션이 붙은 클래스를 찾아서 자동으로 스피링 빈에 등록해준다.
@ComponentScan(
        // basePackages : 탐색할 패키지의 시작 위치 지정 (없으면 모든 자바, 라이브러리 등을 다 탐색해버린다.)
//        basePackages = "hello.core.member", //  member 패키지 파일만 탐색
        basePackages = "hello.core",
        // excludeFilters : 컴포넌트 스캔 대상에서 제외해준다.
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)

)
public class AutoAppConfig {

    @Bean(name = "memoryMemberRepository")
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
}
