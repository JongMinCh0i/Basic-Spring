package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService {

    private final MemberRepository memberRepository = new MemoryMemberRepository();
    // DIP
    private  DiscountPolicy discountPolicy;

    /**
     * 현재 DiscountPolicy(인터페이스) 와 RateDiscountPolicy()(구현체) 모두 의존관계에 놓여있는 상황이다.
     * 이는 OCP( Open-Closed Principle )를 위반한 케이스이다.
     *
     * 따라서 구현체를 삭제하고 인터페이스 (추상화) 를 의존하는 것으로 변경하였다.
     */

//    private final DiscountPolicy discountPolicy = new RateDiscountPolicy(); // OCP 위반


    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        // discountPolicy가 구현(New())되지 않았기에 NPE
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}