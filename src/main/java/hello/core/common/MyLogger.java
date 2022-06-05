package hello.core.common;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.UUID;

// 생성시점은 스프링 요청 시점에 생성
// 빈이 생성되는 시점에 자동으로 @PostConstruct 초기화 메서드를 사용해서 uuid 를 생성해서 저장
// 해당 메서드는 HTTP 당 하나씩 생성되므로, uuid를 저장해두면 다른 HTTP 요청과 구분된다.
// 빈이 소멸되는 시점에 @PreDestory 를 사용해서 종룔 메시지를 남긴다.
// requestURL은 이 빈이 생성되는 시점에는 알 수 없으므로, 외부에서 setter로 입력 받는다.
@Component
@Scope(value = "request") // error Non defined
public class MyLogger {

    private String uuid;
    private String requestURL;

    public void setRequestURL(String requestURL) {
        this.requestURL = requestURL;
    }

    public void log(String message) {
        System.out.println("[" + uuid + "]" + "[" + requestURL + "] " + message);
    }

    // 생성 시점에 스프링 컨테이너에서 호출 메서드
    @PostConstruct
    public void init() {
        String uuid = UUID.randomUUID().toString();
        System.out.println("[" + uuid + "] request scope bean create:" + this);
    }

    // 컨테이너에서 객체를 제거하기 전에 실행
    // prototype과 다르게 HTTPrequest가 terminate 됐을 때 호출
    @PreDestroy
    public void close() {
        System.out.println("[" + uuid + "] request scope bean close:" + this);
    }
}
