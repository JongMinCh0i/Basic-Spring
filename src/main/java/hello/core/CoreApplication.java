package hello.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CoreApplication {

	/*
		스프링 부트는 웹 라이브러리가 없을 경우 AnnotationConfigApplicationContext 기반으로 애플리케이션 구동
		스프링 부트는 웹 라이브러리가 추가 될 경우에는 AnnotationConfigServletWebServerApplicationContext 를 기반으로 구동
	 */


	public static void main(String[] args) {
		SpringApplication.run(CoreApplication.class, args);
	}
}
