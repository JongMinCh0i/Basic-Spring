package hello.core.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

// 스프링 컨테이너에 요청할 때 마다 새로 생성됨
// 종료 메서드가 호출되지 않는다 = 컨테이너가 소멸까지 관여하지 않는다.
// 프로토타입 빈은 프로토타입 빈을 조회한 클라이언트가 관리해야 하며 종료 메서드에 대한 호출도 클라이언트가 직접 명시한다.
public class ProtoTypeTest {


    @Test
    public void prototypeBeanFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
        System.out.println("find prototypeBean1");
        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        System.out.println("find prototypeBean1");
        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);

        System.out.println("prototypeBean1 = " + prototypeBean1);
        System.out.println("prototypeBean2 = " + prototypeBean2);

        Assertions.assertThat(prototypeBean1).isNotSameAs(prototypeBean2);
        ac.close();
    }


    @Scope("prototype")
    static class PrototypeBean {
        // 생성 시점
        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean.init");
        }

        // 종료 시점
        @PreDestroy
        public void destroy() {
            System.out.println("PrototypeBean.destroy");
        }
    }
}