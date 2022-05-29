package hello.core.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


// 스프링 빈의 이벤트 라이프 사이클
// 스프링 컨테이너 생성 -> 스프링 빈 생성 -> 의존관계 주입 -> 초기화 콜백 -> 사용 -> 소멸전 콜백 -> 스프링 종료
// 초기화 콜백 : 빈이 생성되고, 빈의 의존관계 주입이 완료된 후 호출
// 소멸전 콜백 : 빈이 소멸되기 직전에 호출

public class BeanLifeCycleTest {

    @Test
    public void lifeCycleTest() {
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        NetworkClient client = ac.getBean(NetworkClient.class);
        ac.close();
    }

    @Configuration
    static class LifeCycleConfig {

        /*
            @Bean(initMethod =  "init" , destroyMethod = "close")

            설정 정보 사용 특징
            메서드 이름을 자유롭개 줄 수 있다.
            스프링 빈이 스프링 코드에 의존하지 않는다.
            코드가 아니라 설정 정보를 사용하기 때문에 코드를 고칠 수 없는 외부 러이브러리에도 초기화, 종료 메서드를 적용할 수 있다.

            + @Bean의 destoryMethod의 default 값은 추론 타입이다.
            해당 추론 기능(inferred)은 close, shutdown 이란 이름의 메서드를 자동으로 호출해준다.
            따라서 직접 스프링 빈으로 등록하면 종료 메서드는 따로 적어주지 않아도 잘 동작한다.
         */
        @Bean
        public NetworkClient networkClient() {
            NetworkClient networkClient = new NetworkClient();
            networkClient.setUrl("http://hello-spring.dev");
            return networkClient;
        }
    }
}
