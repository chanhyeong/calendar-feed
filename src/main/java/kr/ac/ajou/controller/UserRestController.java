package kr.ac.ajou.controller;

import kr.ac.ajou.model.User;
import kr.ac.ajou.service.SecurityService;
import kr.ac.ajou.service.UserService;
import kr.ac.ajou.service.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by huy on 2016. 11. 17..
 */
@RestController
@RequestMapping("/users")
public class UserRestController {
  @Autowired
  private UserService userService;

  @Autowired
  private SecurityService securityService;

  @Autowired
  private UserValidator userValidator;

  @RequestMapping(value = "/sign_up", method = RequestMethod.GET)
  public ModelAndView signUp(Model model) {
    ModelAndView modelAndView = new ModelAndView("users/sign_up");

//    model.addAttribute("userForm", new User());
    return modelAndView;
  }

  @RequestMapping(value = "/sign_up", method = RequestMethod.POST)
  public ModelAndView userCreate(@ModelAttribute("signUpForm") User user, BindingResult bindingResult, Model model) {
    ModelAndView modelAndView = new ModelAndView();
    userValidator.validate(user, bindingResult);

    if (bindingResult.hasErrors()) {
      modelAndView.setViewName("users/sign_up");
      return modelAndView;
    }

    userService.create(user);

    securityService.autologin(user.getUsername(), user.getPassword());
    modelAndView.setViewName("redirect:/users/sign_in");
    return modelAndView;
  }

  @RequestMapping(value = "/sign_in", method = RequestMethod.GET)
  public ModelAndView signIn(Model model, String error, String logout) {
    if (error != null)
      model.addAttribute("error", "Your username and password is invalid.");

    if (logout != null)
      model.addAttribute("message", "You have been logged out successfully.");
    ModelAndView modelAndView = new ModelAndView("users/sign_up");
    modelAndView.setViewName("users/sign_in");
    return modelAndView;
  }

//  @RequestMapping(method = RequestMethod.GET)
//  public ResponseEntity<Collection<User>> getAllUsers(){
//    return new ResponseEntity<>((Collection<User>) repository.findAll(), HttpStatus.OK);
//  }
}
