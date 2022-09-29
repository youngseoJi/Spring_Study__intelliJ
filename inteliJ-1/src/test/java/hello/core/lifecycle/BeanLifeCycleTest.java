package hello.core.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCycleTest {

    @Test
    public void lifeCycleTest() {
        // LifeCycleConfig 빈으로 등록
        // ConfigurableApplicationContext 상위 인터페이스 부모 = AnnotationConfigApplicationContext 하위 인터페이스 자식 그래서 담을 수 있다.
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        NetworkClient client = ac.getBean(NetworkClient.class);
        ac.close();
        // ConfigurableApplicationContext 닫는 메소드를 사용하기 위한 인터페이스, 기본적으로 인터페이스를 닫는 경우가 없어서 ApplicationContext가 제공하지 않는다.
    }

    @Configuration
    static class LifeCycleConfig {
        @Bean(initMethod = "init", destroyMethod = "close")
        public NetworkClient networkClient() {
            // 생성자 생성 NetworkClient
            NetworkClient networkClient = new NetworkClient();
            networkClient.setUrl("http://hello-spring.dev");
            return networkClient;
        }
    }
}
