package kr.ac.ajou.controller;

/**
 * Created by huy on 2016. 11. 17..
 */
import kr.ac.ajou.model.User;
import kr.ac.ajou.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@RestController
public class WecolmeRestController {
  @Autowired
  private UserService userService;

  @RequestMapping(value = {"/", "/welcome"}, method = RequestMethod.GET)
  public ModelAndView welcome(Principal principal) {
    User user = userService.findByUsername(principal.getName());
    ModelAndView modelAndView = new ModelAndView("welcome");
    modelAndView.addObject("currentUser", user);
    return modelAndView;
  }
}