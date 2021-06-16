package br.com.alura.forum;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;


@SpringBootTest
@ActiveProfiles(value = "test")
public class ForumApplicationTests {

	@Test
	public void contextLoads() {
	}

}
