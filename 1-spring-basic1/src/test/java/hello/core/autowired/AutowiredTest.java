package hello.core.autowired;

import hello.core.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;

import java.util.Optional;

public class AutowiredTest {

    @Test
    void AutowiredOption(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);

    }

    static class TestBean {
        //호출 안됨 (required = false) : 자동주입할 대상, 의존관계가 없을경우 해당 메소드자체가 호출 안된다.
        @Autowired(required = false)
        public void setNoBean1(Member noBean1) {
            System.out.println("noBean1 = " + noBean1); // 호출 X
        }
        //null 호출 (@Nullable) :  자동주입할 대상, 의존관계가 없을경우 해당 메소드가 호출되나 "null"로 들어온다.
        @Autowired
        public void setNoBean2(@Nullable Member noBean2) {
            System.out.println("noBean2 = " + noBean2); //noBean3 = Optional.empty
        }
        //Optional.empty 호출  (Optional<>) :  자동주입할 대상, 의존관계가 없을경우 "Optional.empty"을 넣어주고 있으면 해당 값이 들어온다.
        @Autowired(required = false )
        public void setNoBean3(Optional<Number> noBean3){
            System.out.println("noBean3 = " + noBean3); //noBean3 = Optional.empty
        }

    }
}
