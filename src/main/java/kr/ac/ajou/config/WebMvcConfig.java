package kr.ac.ajou.config;

import kr.ac.ajou.thymeleaf.ThymeleafLayoutInterceptor;
import nz.net.ultraq.thymeleaf.LayoutDialect;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;
import org.thymeleaf.templateresolver.TemplateResolver;

/**
 * Created by huy on 2016. 11. 17..
 */

@Configuration
@EnableWebMvc
@ComponentScan("kr.ac.ajou")
public class WebMvcConfig extends WebMvcConfigurerAdapter {
  @Bean
  public ViewResolver getViewResolver() {
    ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
    viewResolver.setTemplateEngine(templateEngine());
    viewResolver.setOrder(1);
    viewResolver.setCharacterEncoding("UTF-8");
    viewResolver.setContentType("text/html; charset=UTF-8");
    return viewResolver;
  }

  @Bean
  public TemplateResolver templateResolver() {
    ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver();
    templateResolver.setPrefix("/WEB-INF/views/");
    templateResolver.setSuffix(".html");
    templateResolver.setTemplateMode("HTML5");
    templateResolver.setCharacterEncoding("UTF-8");
    return templateResolver;
  }

  @Bean
  public SpringTemplateEngine templateEngine() {
    SpringTemplateEngine templateEngine = new SpringTemplateEngine();
    templateEngine.setTemplateResolver(templateResolver());
    templateEngine.addDialect(new LayoutDialect());
    templateEngine.setMessageSource(messageSource());

    return templateEngine;
  }

  @Bean
  public MessageSource messageSource()
  {
    ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
    messageSource.setBasename("locale/messages");
    messageSource.setDefaultEncoding("UTF-8");
    messageSource.setCacheSeconds(3600);

    return messageSource;
  }

  @Override
  public void configureDefaultServletHandling(
      DefaultServletHandlerConfigurer configurer) {
    configurer.enable();
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(new ThymeleafLayoutInterceptor());
  }

//  https://spring.io/blog/2014/01/03/utilizing-webjars-in-spring-boot
  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    if (!registry.hasMappingForPattern("/webjars/**")) {
      registry.addResourceHandler("/webjars/**").addResourceLocations(
          "classpath:/META-INF/resources/webjars/");
    }
    if (!registry.hasMappingForPattern("/static/**")) {
      registry.addResourceHandler("/static/**").addResourceLocations(
          "/static");
    }
  }
}