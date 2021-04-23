package hello.core.autowired;

import hello.core.member.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;

import java.util.Optional;

public class AutoWiredTest {

    @Test
    @DisplayName("오토와이어드 옵션")
    void autowiredOption(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);
        //@Autowired(required=false):자동 주입할 대상이 없으면 수정자 메서드 자체가 호출이 안 됨
        //org.sprinframework.lang.@Nullable : 자동 주입할 대상이 없으면 null이 입력된다.
        //Optional<>:자동 주입할 대상이 없으면 Optional.empty가 입력된다.

    }

    static class TestBean{
        /*
        @Autowired //기본값 true -> 예외터짐. 찾을 수 없다고.
        public void setNoBean1(Member noBean1){ //@No Componet인 Member를 주입받고 있다.
            System.out.println("noBean1 = " + noBean1);
        }
        */

        @Autowired(required =false) //의존관계 없다? 아예 호출 안 됨
        public void setNoBean1(Member noBean1){ //@No Componet인 Member를 주입받고 있다.
            System.out.println("noBean1 = " + noBean1);
        }

        @Autowired //@Nullable 없으면 null 채워줌.
        public void setNoBean2(@Nullable Member noBean2){
            System.out.println("noBean2 = " + noBean2);
        }

        @Autowired //Optional.empty 자바8 문법
        public void setNoBean3(Optional<Member> noBean3){
            System.out.println("noBean2 = " + noBean3);
        }
    }
}
