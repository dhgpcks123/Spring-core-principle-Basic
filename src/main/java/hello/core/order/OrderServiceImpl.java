package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService {

    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }
    //누군가 클라이언트의 OrderServiceImpl에 DiscountPolicy의 구현 객체를 대신 생성하고 주입해줘야 한다!
    //의존 관계를 주입해준다 (DI - Dependency Injection) 의존 관계 주입
    //생성자를 통해 어떤 구현 객체가 들어올지(주입 될지) 전혀 알 수 없다. 결정은 외부(AppConfig)에서 한다.

//    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
//    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();
    /*
        우리는 역할과 구현을 충실하게 분리햇다. ok
        다형성도 활용하고, 인터페이스와 구현 객체를 분리했다 ok.
        OCP, DIP 같은 객체지향 설계 원칙을 충실히 준수했다.
            -> 그렇게 보이지만.. 사실은 아니다.
        DIP : 주문서비스 클라이언트('OrderServiceImpl')는 'DiscountPolicy' 인터페이스에 의존하면서 DIP를지킨 것 같은데?
            -> 클래스 의존관계를 분석해보자. 추상(인터페이스)뿐만 아니라 구체(구현) 클래스에도 의존하고 있다.
                -추상(인터페이스) 의존 : DiscountPolicy
                -구체(구현) 클래스 : FixDiscountPolicy, RateDiscountPolicy
            -> DIP 위반
         OCP : 변경하지 않고 확장할 수 있다고 했는데!
            -> 지금 코드는 기능을 확장해서 변경하면, 클라이언트 코드에 영향을 준다! 따라서 OCP를 위반한다!
     */

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
    
    //Test 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
