package hello.core;

/*
    @ComponentScan

    ComponentScan 을 사용하면  @Component 가 붙은 클래스를 찾아서 자동으로 Bean 등록 함
    아래의 경우  @Component 가 붙은 클래스 중 제외 할 것들을 명시해주는데
    해당 @ComponentScan.Filter 는 Configuration.class 를 제외 했다.
    @Configuration 의 경우 내부에서 @Component 가 붙어있기에 자동으로  조회대상이기 때문이다.

    제외 하지 않았을 경우  (AppConfig, TestConfig)의 경우 자동으로 등록 되며 내부의 Bean으로 등록된
    클래스들 역시 자동으로 등록된다.

    ComponentScan은 말 그대로 Component 애노테이션이 붙은 클래스를 스캔해서 스프링 빈으로 자동 등록한다.
    자동 등록 되길 원할경우 @Componet 사용

    @Autowired
    기존 AppConfig(DI Container)를 통해서 빈등록을 처리하였으나
    @Component를 통해 해당 클래스는 자동으로 빈등록이 처리하게 되었다.
    이 경우 AppConfig를 통해 이뤄지던 의존성 주입(의존관계 명시)을 @Component 가 등록된 클래스 내부에서
    처리하게 되는데 등록된 클래스 내부에서 @Autowired를 생성자 상위에 적을 경우 의존관계를 자동으로 주입한다.
 */

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        /*   basePackages= "hello.core.member" [scan 시작 위치 설정]
             basePackageClasses = AutoAppConfig.class [지정한 클래스의 패키지를 탐색 시작 위치로 설정]
             basePackageClasses = AutoAppConfig.class ,
             basePackages= "hello.core.member",
             스캔위치를 지정하지 않을경우 @ComponentScan 이 붙은 설정 정보 클래스의 패키지가 시작 위치가 된다.(hello.core)
         */


        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class))

public class AutoAppConfig {  }