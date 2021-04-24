package hello.core.order;

import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class OrderServiceImplTest {

    @Test
    void createOrder(){
        MemoryMemberRepository memberRepository = new MemoryMemberRepository();
        memberRepository.save(new Member(1L,"name", Grade.VIP));
        OrderServiceImpl orderService = new OrderServiceImpl(memberRepository, new FixDiscountPolicy());
        Order order = orderService.createOrder(1L, "iteamA", 10000);
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);

        //값이 셋팅 안되어져있음. memberRepository와 discountPolicy .
        //그럼 nullPointerException 뜰 수 밖에 없지.
        //테스트 짤 때 안보여서 이런 실수를 할 수 밖에.. 들어가서 봐야함.
        //그래서 그냥 생성자주입 살려서 쓰면? 그럼 해주지.
    }
}
