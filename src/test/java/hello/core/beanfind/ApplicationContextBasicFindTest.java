package hello.core.beanfind;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ApplicationContextBasicFindTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("빈 이름으로 조회")
    void findBeanByName(){
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        System.out.println("memberService = " + memberService);
        System.out.println("memberService.getClass() = " + memberService.getClass());
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("이름 없이 타입으로만 조회")
    void findBeanByType(){
        MemberService memberService = ac.getBean(MemberService.class);
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("구체 타입으로 조회")
    void findBeanByName2(){
        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }
    //구체적인 거 적는 건 별로 안 좋아. 구현체 거 가져와서 테스트는 가능! 구현에 의존하는거니까. 역할에 의존하게 해야지!

    //Test는 꼭 실패 테스트도 조회해야 한다.
    @Test
    @DisplayName("빈 이름으로 조회x")
    void findBeanByNameX(){
        //ac.getBean("xxxx", MemberService.class);
        //여기서 org.springframework.beans.factory.NoSuchBeanDefinitionException: No bean named 'xxxx' available
        assertThrows(NoSuchBeanDefinitionException.class,
                () ->ac.getBean("xxxx", MemberService.class));
        //오른쪽에 있는 거 실행해서 예외(NoSuchBeanDefinitionException)가 터져야. 이건 성공한 것!
    }
}
