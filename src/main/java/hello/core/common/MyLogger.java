package hello.core.common;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.UUID;

// 생성시점은 스프링 요청 시점에 생성
@Component
@Scope(value = "request")
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
    @PreDestroy
    public void close() {
        System.out.println("[" + uuid + "] request scope bean close:" + this);
    }


}
