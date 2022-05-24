package hello.core.scan.filter;


import java.lang.annotation.*;

@Target(ElementType.TYPE) // TYPE 지정을 통해 필드에 붙음
@Retention(RetentionPolicy.RUNTIME)
@Documented

public @interface MyIncludeComponent {



}
