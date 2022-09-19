package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

// 설정정보
@Configuration
// 컴포넌트 스캔 : @Component 애노테이션이 붙은 클래스를 찾아서 자동으로 스피링 빈에 등록해준다.
@ComponentScan(
        // excludeFilters : 컴포넌트 스캔 대상에서 제외해준다.
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
        // Ap b
)
public class AutoAppConfig {

}
