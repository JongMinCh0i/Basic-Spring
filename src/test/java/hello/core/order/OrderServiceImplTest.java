package hello.core.order;

import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class OrderServiceImplTest {

    // 순수한 자바 코드를 사용한 자바 테스트코드,
    // 가능한 Constructor DI 를 사용하여 누락없이 테스트 실행
    @Test
    void createOrder() {


        // DI setter
//        Member member = new Member(1L, "Kim", Grade.VIP);
//        MemoryMemberRepository mm = new MemoryMemberRepository();
//        mm.save(member);
//
//        OrderServiceImpl orderService = new OrderServiceImpl();
//        orderService.setMemberRepository(mm);
//        orderService.setDiscountPolicy(new FixDiscountPolicy());


        // DI Constructor

        MemoryMemberRepository memberRepository = new MemoryMemberRepository();
        memberRepository.save(new Member(1L, "KIM", Grade.VIP));

        OrderServiceImpl orderService = new OrderServiceImpl(memberRepository, new FixDiscountPolicy());

        Order order = orderService.createOrder(1L, "Kim", 10000);

        // Test
        assertThat(order.getDiscountPrice()).isEqualTo(1000);

    }
}