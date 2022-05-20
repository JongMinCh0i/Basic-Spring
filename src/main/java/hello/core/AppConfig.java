package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 구성영역 (생성의 역활(객체의 주입))
@Configuration
public class AppConfig {

    // 생성자 주입
    @Bean
    public MemberService memberService() {
        return new MemberServiceImpl(MemberRepository());
    }

    // 생성자 주입
    @Bean
    public MemberRepository MemberRepository() {
        return new MemoryMemberRepository();
    }

    // 생성자 주입
    @Bean
    public OrderService orderService() {
        return new OrderServiceImpl(new MemoryMemberRepository(), discountPolicy());
    }

    // 생성자 주입
    @Bean
    public DiscountPolicy discountPolicy() {
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }
}