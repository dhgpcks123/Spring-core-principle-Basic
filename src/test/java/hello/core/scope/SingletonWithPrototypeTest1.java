package hello.core.scope;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static org.assertj.core.api.Assertions.assertThat;

public class SingletonWithPrototypeTest1 {


    @Test
    void prototypeFind(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        prototypeBean1.addCount();
        assertThat(prototypeBean1.getCount()).isEqualTo(1);

        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
        prototypeBean2.addCount();
        assertThat(prototypeBean2.getCount()).isEqualTo(1);
        ac.close();
    }

    @Test
    void singletonClientUserPrototype(){
        AnnotationConfigApplicationContext ac =
                new AnnotationConfigApplicationContext(PrototypeBean.class, ClientBean.class);
        ClientBean clientBean1 = ac.getBean(ClientBean.class);
        int count1 = clientBean1.logic();
        assertThat(count1).isEqualTo(1);

        ClientBean clientBean2 = ac.getBean(ClientBean.class);
        int count2 = clientBean2.logic();
        assertThat(count2).isEqualTo(1);
    }

    @Scope("singleton")
    static class ClientBean{
//        private final PrototypeBean prototypeBean; //생성시점에 주입

        @Autowired
        private ObjectProvider<PrototypeBean> prototypeBeansProvider;
//        private Provider<PrototypeBean> prototypeBeansProvider;


//        @Autowired
//        ApplicationContext applicationContext; //무식하게 그냥 받아버리는 방법도 있긴 함. -> 해결방법이 뭘까?
//        @Autowired
//        public ClientBean(PrototypeBean prototypeBean){
//            this.prototypeBean = prototypeBean;
//        }
        public int logic(){
//            PrototypeBean prototypeBean = applicationContext.getBean(PrototypeBean.class);
//            PrototypeBean prototypeBean = prototypeBeansProvider.get(); // 자바표준 provider
            PrototypeBean prototypeBean = prototypeBeansProvider.getObject(); //스프링 꺼 ObjectProvider
            prototypeBean.addCount();
            return prototypeBean.getCount();
        }
    }

    @Scope("prototype")
    static class PrototypeBean{
        private int count = 0;

        public void addCount(){
            count++;
        }

        public int getCount(){
            return count;
        }

        @PostConstruct
        public void init(){
            System.out.println("PrototypeBean.init " + this);
        }

        @PreDestroy
        public void destroy(){
            System.out.println("PrototypeBean.destroy");
        }
    }
}
