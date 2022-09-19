package hello.core.scan.filter;

import java.lang.annotation.*;

@Target(ElementType.TYPE) //TYPE이면 클래스 레벨에 붙는다. 설정하는 것
@Retention(RetentionPolicy.RUNTIME)
@Documented

// 컴포넌트 스캔에서 제외
public @interface MyExcludeComponent {


}
