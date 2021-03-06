package hello.core.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static org.assertj.core.api.Assertions.assertThat;

public class SingleTonTest {


    @Test
    void singletonBeanFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SingletonBean.class);

        SingletonBean singletonBean1 = ac.getBean(SingletonBean.class);
        SingletonBean singletonBean2 = ac.getBean(SingletonBean.class);
        System.out.println("singletonBean1 = " + singletonBean1);
        System.out.println("singletonBean2 = " + singletonBean2);

        assertThat(singletonBean1).isEqualTo(singletonBean2);

        ac.close();

    }

    @Scope("singleton")
    static class SingletonBean {
        // 생성 시점
        @PostConstruct
        public void init() {
            System.out.println("SingletonBean.init");
        }

        // 종료 시점
        @PreDestroy
        public void destroy() {
            System.out.println("SingletonBean.destroy");
        }
    }

}

