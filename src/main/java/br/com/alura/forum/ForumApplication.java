package br.com.alura.forum;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
/* Enable support for Spring get url parameter and perform pagination */
@EnableSpringDataWebSupport
/* Enable Cache */
@EnableCaching
/* Enable Swagger (API Documentation Management) */
@EnableSwagger2
/* SpringBootServletInitializer add support to generate a war file and use an Application Server */
public class ForumApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(ForumApplication.class, args);
	}

	/* After extends SpringBootServletInitializer and override configure method, we have added support to servlet 3.0
	* Note: It is necessary to declare tomcat dependency into pom.xml within provided scope.
	* */
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(ForumApplication.class);
	}
}
