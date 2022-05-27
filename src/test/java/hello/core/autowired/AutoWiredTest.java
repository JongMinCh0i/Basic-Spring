package hello.core.autowired;

import hello.core.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;

import java.util.Optional;

public class AutoWiredTest {


    @Test
    void AutowiredOption() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);

    }

    // Member Class 를 @AutWired를 통해 자동 주입하려고 하지만
    // Member Class 는 등록된 Spring Bean 아니기에 아래의 3개의 메소드 모두 정상 작동하지 않는다.
    // 정상 작동하지 않는 @Autowired의 옵션처리 3가지에 대해 알아본다.

    static class TestBean {

        // 자동 주입할 대상이 없으면 수정자 메서드 자체가 호출 안됨 // 기본 값 @Autowired(required = ture)
        @Autowired(required = false)
        public void setNoBean1(Member noBean1) {
            System.out.println("noBean1 = " + noBean1);
        }

        // 자동 주입할 대상이 없으면 null이 입력된다.
        // 호출은 되지만 null로 들어온다.
        @Autowired
        public void setNoBean2(@Nullable Member noBean2) {
            System.out.println("noBean2 = " + noBean2);
        }

        // 자동 주입할 대상이 없으면 Optional.empty 가 입력된다.
        // 값이 있을 경우 해당 값을 입력해주나 Member class 는 @Component로 등록된 bean 이 아니기에 null 호출
        @Autowired
        public void setNoBean3(Optional<Member> noBean3) {
            System.out.println("noBean3 = " + noBean3);
        }
    }


}
