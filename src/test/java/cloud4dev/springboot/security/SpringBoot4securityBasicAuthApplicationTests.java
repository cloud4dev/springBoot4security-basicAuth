package cloud4dev.springboot.security;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cloud4dev.springboot.security.api.RestBuilder;
import cloud4dev.springboot.security.api.Uris;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBoot4securityBasicAuthApplicationTests {

	private static final String url = "http://localhost:8080/api/v0";
	
	@Test
	public void contextLoads() {
	}

	
	@Test
	public void testUserOk(){
		String response = new RestBuilder<String>(url).path(Uris.ADMIN).basicAuth("user", "user").clazz(String.class).get().build();
		
		System.out.println("INFO >>>> " + response);
	}
}