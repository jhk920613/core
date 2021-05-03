package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        excludeFilters = @ComponentScan.Filter(
                type = FilterType.ANNOTATION, classes = Configuration.class
        )  // 컴포넌트 스캔 대상에서 제외할 것을 지정, @Configuration도 @Component 가 있음, 기존 예제 코드를 살리기 위해 했고 보통은 안함
)
public class AutoAppConfig {
}
