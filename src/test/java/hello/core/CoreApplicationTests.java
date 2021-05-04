package hello.core;

import hello.core.order.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CoreApplicationTests {

	@Autowired	// 이런데에서나 이 방식을 쓰길 추천
	OrderService orderService;

	@Test
	void contextLoads() {
	}

}
