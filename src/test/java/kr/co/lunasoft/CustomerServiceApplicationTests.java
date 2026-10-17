package kr.co.lunasoft;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = {
		"eureka.client.enabled=false",
		"management.tracing.export.enabled=false"
})
class CustomerServiceApplicationTests {

	@Test
	void contextLoads() {
	}

}
