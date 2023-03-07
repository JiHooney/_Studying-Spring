package hello.core.beanfind;

import hello.core.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationContextInfoTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("모든 빈 출력하기")
    void findAllBean() {
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();

        for (String beanDefinitionName : beanDefinitionNames) {
            Object bean = ac.getBean(beanDefinitionName);

            System.out.println("name = " + beanDefinitionName + " object = " + bean );
        }
    }

    @Test
    @DisplayName("애플리케이션 빈 출력하기")
    void findApplicationBean() {
        //ac.getBeanDefinitionNames() : 스프링에 등록된 모든 빈 이름을 출력한다.
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();

        for (String beanDefinitionName : beanDefinitionNames) {
            //빈에 대한 메타데이터 정보를 꺼내기 위해 ac.getBeanDefinition을 사용하고
            //매개변수로 beanDefinitionName을 넣어줘야 한다.
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);

            //스프링이 내부에서 사용하기 위한 빈이 아니라
            //개발자가 애플리케이션을 만들기 위해 등록한 빈들을 출력한다.
            //혹은 외부 라이브러리들을 의미한다.
            //즉 이 경우 우리가 AppConfig에 등록한 5개의 빈들만 출력이 된다.
            //ROLE_APPLICATION : 사용자가 직접 등록한 애플리케이션 빈
            //ROLE_INFRASTRUCTURE : 스프링이 내부적으로 사용하는 빈
            if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
                Object bean = ac.getBean(beanDefinitionName);

                System.out.println("name = " + beanDefinitionName + " / object = " + bean );
            }
        }
    }




}
