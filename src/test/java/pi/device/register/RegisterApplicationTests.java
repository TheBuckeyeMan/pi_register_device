package pi.device.register;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(properties = {
    "aws.api-gateway.key=dummy-test-key",
    "aws.api-gateway.endpoint=http://localhost/test",
    "pi.device.info.path=/dev/null"
})
class RegisterApplicationTests {

	@Test
	void contextLoads() {
	}

}
