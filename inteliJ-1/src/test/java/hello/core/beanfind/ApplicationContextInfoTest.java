package hello.core.beanfind;

import hello.core.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

class ApplicationContextInfoTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
   @Test // 빈 테스트 - 컨테이너에 등록된 모든 빈 조회
   @DisplayName("모든 빈 출력하기")
   void findApplicationBean() {
       String[] beanDefinitionNames = ac.getBeanDefinitionNames(); // 정의된 빈 이름을 꺼낼 수 있다.
       for (String beanDefinitionName : beanDefinitionNames) { // iter + tap 자동 for문 생성
           BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);
           // 스프링이 내부에서 사용하는 빈은 getRole()로 구분
           // - ROLE_APPLICATION : 일반적으로 사용자가 정의한 빈
           // - ROLE_INFRASTRUCTURE : 스프링이 내부에서 사용하는 빈

           // 직접 등록한 빈만 조회하기 ROLE_APPLICATION
           if(beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
               Object bean = ac.getBean(beanDefinitionName); // 빈이름으로 조회
               System.out.println("name = " + beanDefinitionName + " object=" + bean);
           }
       }
   }
}
