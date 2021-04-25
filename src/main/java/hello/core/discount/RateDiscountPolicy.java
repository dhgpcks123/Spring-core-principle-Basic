package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;


@Component
@Primary
//@Qualifier("mainDiscountPolicy")
//@Primary
//@Primary는 우선권을 주는 것! 지저분하게 뭐 안 붙여도 되니까 좋지.
//메인 데이터베이스 90%쓰고, 보조 데이터베이스 10%쓴다. 그냥 똑깥이 쭈욱 가져와서 @Primary쓰면... 메인 DB쓰다가 없다? 그러면 보조 DB를 쓰게 하는 방식!
//@Qualifier는 어노테이션 붙여줘야해서 코드가 조금 더러워짐
//우선권은 @Primary보다 @Quilifier가 높다. 직접적으로 명시하고 있기 때문에 우선순위가 높음.
public class RateDiscountPolicy implements DiscountPolicy{

    private int discountPercent =10;

    @Override
    public int discount(Member member, int price) {
        if(member.getGrade() == Grade.VIP){
            return price * discountPercent / 100;
        } else{
            return 0;
        }
    }
}
