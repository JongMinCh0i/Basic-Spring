package hello.core.singleton;

import com.sun.source.tree.AssertTree;
import hello.core.AppConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.assertj.core.api.Assertions.*;

class StatefulServiceTest {

    @Test
    void statefulServiceSingleton() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        // Thread A
        int userA = statefulService1.order("UserA", 10000);
        // Thread B
        int userB = statefulService2.order("UserB", 20000);

        //ThreadA : 사용자 A 주문 금액 조회
//        int price = statefulService1.getPrice();

//        assertThat(statefulService1.getPrice()).isEqualTo(20000);

        assertThat(userA).isNotSameAs(userB);
    }


    static class TestConfig {

        // Spring Bean 은 항상 무상태로 설계해야함(Stateless)
        // 공유 필드(static)로 인한 덮어쓰기로 값의 변화가 예상치 못한 곳에서 터질 수 있음

        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }
}