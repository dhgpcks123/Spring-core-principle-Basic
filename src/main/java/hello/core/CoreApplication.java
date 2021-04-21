package hello.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//여기 까보면 @ComponentScan도 들어있다. 그래서 굳이 AutoAppConfig같은 파일을 만들 필요가 없는 것 같은데?
public class CoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoreApplication.class, args);
	}

}
