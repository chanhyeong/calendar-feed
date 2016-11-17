package kr.ac.ajou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class CalendarFeedApplication extends SpringBootServletInitializer {

  @Override
  protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
    return application.sources(CalendarFeedApplication.class);
  }

	public static void main(String[] args) {
    ApplicationContext ctx = SpringApplication.run(CalendarFeedApplication.class, args);

    System.out.println("Let's inspect the beans provided by Spring Boot:");

    String[] beanNames = ctx.getBeanDefinitionNames();
//    Arrays.sort(beanNames);
//    for (String beanName : beanNames) {
//      System.out.println(beanName);
//    }
	}
}
