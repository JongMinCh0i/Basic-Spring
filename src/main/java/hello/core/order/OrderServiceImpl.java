package hello.core.order;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

//@RequiredArgsConstructor
@Component
public class OrderServiceImpl implements OrderService {

    // DIP
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    // 생성자 주입
    // 불변, 필수 의존관계에 사용됨
    // 생성자 호출시점에 딱 1번만 호출되는 것이 보장됨

    // Setter DI
//    @Autowired
//    public void setMemberRepository(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }
//
//    @Autowired
//    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
//        this.discountPolicy = discountPolicy;
//    }

//     Constructor DI (lombok의 @RequiredArgsConstructor로 대체 )
    public OrderServiceImpl(MemberRepository memberRepository,  @MainDiscountPolicy DiscountPolicy rateDiscountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = rateDiscountPolicy;
    }

    /**
     * 현재 DiscountPolicy(인터페이스) 와 RateDiscountPolicy()(구현체) 모두 의존관계에 놓여있는 상황이다.
     * 이는 OCP( Open-Closed Principle )를 위반한 케이스이다.
     * <p>
     * 따라서 구현체를 삭제하고 인터페이스 (추상화) 를 의존하는 것으로 변경하였다.
     */

//  private final DiscountPolicy discountPolicy = new RateDiscountPolicy(); // OCP 위반

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {

        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    // 테스트 용도
    // 현재 사용하고 있는 Repository 확인
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }

}