package kr.ac.ajou.controller;

/**
 * Created by huy on 2016. 11. 17..
 */
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class HelloController {

  @RequestMapping("/")
  public String index() {
    return "Greetings from Spring Boot!";
  }

}