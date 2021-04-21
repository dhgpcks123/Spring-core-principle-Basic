package hello.core;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        basePackages = "hello.core", //어디서 부터 찾는지 지정할 수 있음.+ //자바코드 전부 다, 라이브러리 등도 다 뒤집어보거든!
        //basePacakages = {"hello.core", "hello.service"} 이렇게 여러 시작 위치를 지정할 수도 있다.
        basePackageClasses = AutoAppConfig.class, //얘가 갖고 있는... package hello.core; 여기서 부터 시작
        //default는 어디인가!? : @ComponentScan붙인 곳에 달려 있는... package hello.core; 여기가 시작 위치가 됨.
        //관례 : 개인적으로 즐겨 사용하는 방법은 패키지 위치를 지정하지 않고, 설정 정보 클래스의 위치를
        //프로젝트 최상단에 두는 것이다. 최근 스프링 부트도 이 방법을 기본으로 제공한다. (현재 위치)
        //프로젝트 메인 설정 정보는 프로젝트를 대표하는 정보이기 때문에 프로젝트 시작 루트 위치에 두는 것이 좋다고 생각함!
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes= Configuration.class)
)
//컴포넌트 스캔은 @Component애노테이션이 붙은 클래스를 스캔해서 스프링 빈으로 자동으로 등록한다.
//@Configuration이 컴포넌트의 대상이 된 이유도, @Configuration을 열어보면 @Component 애노테이션이 붙어ㅣㅆ기 때문ㅇ!
public class AutoAppConfig {

//    @Bean(name = "memoryMemberRepository")
//    MemberRepository memberRepository(){
//        return new MemoryMemberRepository();
//    }
    /*
    컴포넌트 스캔 기본 대상
    컴포넌트 스캔은 @Component뿐만 아니라 다음과 같은 내용도 추가로 대상에 포함한다.
    @Component :  컴포넌트 스캔에서 사용
    @Controller: 스프링 MVC 컨트롤러에서 사용 ///// 스프링 MVC 컨트롤러 인식
    @Service : 스프링 비즈니스 로직에서 사용 - ?  ////// 개발자들이 핵심 비즈니스 로직 여기구나! 인식하게 해줌
    @Repository : 스프링 데이터 접근 계층에서 사용 - ? ///// 스프링 데이터 접근 계층으로 인식하고, 데이터 계층의 예외를 스프링 예외로 변환해준다.
    @Configuration : 스프링 설정 정보에서 사용 ///// 스프링 설정 정보를 인식하고, 스프링 빈이 싱글톤을 유지하도록 추가 처리를 해준다.
     얘네 추상화도 @Component 붙어있는 걸 확인할 수 있다.

     참고 : 사실 애노테이션에는 상속관계라는 것이 없다. 그래서 이렇게 애노테이션이 특정 애노테이션 들고 있는 것을
     인식할 수 있는 것은 자바가 지원하는 기능이 아니고, 스프링이 지원하는 기능이다.
     */
}
