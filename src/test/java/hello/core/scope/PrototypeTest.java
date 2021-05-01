package hello.core.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class PrototypeTest {

    @Test
    void prototypeBeanFind(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ProtoType.class); //직접 컴포넌트 스캔하는거라서.. @Componet업서도 됨
        System.out.println("find protoTypeBean1");
        ProtoType prototype1 = ac.getBean(ProtoType.class);
        System.out.println("find protoTypeBean2");
        ProtoType prototype2 = ac.getBean(ProtoType.class);

        System.out.println("prototype1 = " + prototype1);
        System.out.println("prototype2 = " + prototype2);

        Assertions.assertThat(prototype1).isNotSameAs(prototype2);

        ac.close();
    }

    @Scope("prototype")//프로토 타입
    static class ProtoType{
        @PostConstruct
        public void init(){
            System.out.println("prototype.init");
        }
        @PreDestroy
        public void destory(){
            System.out.println("prototype.destory");
        }
    }
}
