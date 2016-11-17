package kr.ac.ajou.controller;

/**
 * Created by huy on 2016. 11. 17..
 */
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class WecoleRestController {

  @RequestMapping(value = {"/", "/welcome"}, method = RequestMethod.GET)
  public String welcome(Model model) {
    return "welcome";
  }
}