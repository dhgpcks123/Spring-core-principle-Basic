package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes= Configuration.class)
)
//컴포넌트 스캔은 @Component애노테이션이 붙은 클래스를 스캔해서 스프링 빈으로 자동으로 등록한다.
//@Configuration이 컴포넌트의 대상이 된 이유도, @Configuration을 열어보면 @Component 애노테이션이 붙어ㅣㅆ기 때문ㅇ!
public class AutoAppConfig {

}
