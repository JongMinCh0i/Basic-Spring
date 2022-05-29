package hello.core.lifecycle;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;


/**
 * InitializingBean, DisposableBean
 * 초기화, 소멸 인터페이스 단점
 * 이 인터페이스는 스프링 전용 인터페이스이며 해당 코드가 스프링 전용 인터페이스에 의존한다.
 * 초기화, 소멸 메서드의 이름을 변경할 수 없다.
 * 내가 코드를 고칠 수 없는 외부 라이브러리에 적용할 수 없다.
 * <p>
 * 초창기 방식 이며 거의 사용하지 않음
 */


/**
// * @PostConstruct, @PreDestroy
    최신 스프링에서 권장하는 방법, 애노테이션을 통해 사용
    컴포넌트 스캔과도 잘 어울리나. 유일한 단점은 외부 라이브러리에는 적용할 수 없다는 것,
    코드를 고칠 수 없는 외부 라이브러리를 초기화, 종료 해야 할 경우에는 @Bean initMethod, destroyMethod 사용
    스프링 종속적인 기술이 아닌 JSR-250이라는 자바 표준, 따라서 스프링이 아닌 다른 컨테이너에서도 동작

 */


public class NetworkClient {

    private String url;

    public NetworkClient() {
        System.out.println("생성자를 호출, url = " + url);

    }

    public void setUrl(String url) {
        this.url = url;
    }

    // 서비스 시작시 호출
    public void connect() {
        System.out.println("connect : " + url);
    }

    public void call(String message) {
        System.out.println("call : " + url + " message : " + message);
    }

    // 서비스 종료시호출
    public void disconnect() {
        System.out.println("close : " + url);
    }

    // 의존 관계 주입이 종료되면 호출
    @PostConstruct
    public void init() {
        System.out.println("NetworkClient.init");
        connect();
        call("초기화 연결 메시지");
    }

    @PreDestroy
    public void close() {
        System.out.println("NetworkClient.close");
        disconnect();
    }
}