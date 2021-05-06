package hello.core.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static org.assertj.core.api.Assertions.*;

public class SingletonWithProtoTypeTest1 {

    @Test
    void prototypeFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);

        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        prototypeBean1.addCount();
        assertThat(prototypeBean1.getCount()).isEqualTo(1);

        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
        prototypeBean2.addCount();
        assertThat(prototypeBean2.getCount()).isEqualTo(1);
    }

    @Test
    void singletonClientUseProtytype() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ClientBean.class, PrototypeBean.class);
        ClientBean clientBean1 = ac.getBean(ClientBean.class);
        int count1 = clientBean1.logic();
        assertThat(count1).isEqualTo(1);

        ClientBean clientBean2 = ac.getBean(ClientBean.class);
        int count2 = clientBean2.logic();
        assertThat(count2).isEqualTo(2);
    }

    @Scope("singleton")
    static class ClientBean {
        private final PrototypeBean prototypeBean;  // 생성 시점에 주입, 주입 이후 계속 같은걸 사

        @Autowired
        public ClientBean(PrototypeBean prototypeBean) {
            this.prototypeBean = prototypeBean;
        }

        public int logic() {
            prototypeBean.addCount();

            return prototypeBean.getCount();
        }
        // logic 을 호출할 때 마다 prototypeBean 이 새로 생성되길 원하면 무식하지만 아래 방법을 사용해야 함
        // 더 좋은 해결 방법이 있으므로 이 방식은 사용하지 말 것
//        @Autowired
//        ApplicationContext applicationContext;
//
//        public int logic() {
//            PrototypeBean prototypeBean = applicationContext.getBean(PrototypeBean.class);
//            prototypeBean.addCount();
//            return prototypeBean.getCount();
//        }
    }

    // 이 것도 빈 등록하면 주입받는 prototypeBean은 위 prototypeBean과는 다른 인스턴스
    @Scope("singleton")
    static class ClientBean2 {
        private final PrototypeBean prototypeBean;  // 생성 시점에 주입, 주입 이후 계속 같은걸 사용

        @Autowired
        public ClientBean2(PrototypeBean prototypeBean) {
            this.prototypeBean = prototypeBean;
        }

        public int logic() {
            prototypeBean.addCount();

            return prototypeBean.getCount();
        }
    }

    @Scope("prototype")
    static class PrototypeBean {
        private int count = 0;

        public void addCount() {
            count++;
        }

        public int getCount() {
            return count;
        }

        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean.init " + this);
        }

        @PreDestroy
        public void destroy() {
            System.out.println("PrototypeBean.destroy ");
        }
    }


}
