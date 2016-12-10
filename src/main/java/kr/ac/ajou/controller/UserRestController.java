package kr.ac.ajou.controller;

import kr.ac.ajou.model.User;
import kr.ac.ajou.service.SecurityService;
import kr.ac.ajou.service.UserService;
import kr.ac.ajou.service.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
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
  private UserValidator userValidator;

  @RequestMapping(value = "/sign_up", method = RequestMethod.GET)
  public ModelAndView signUp(Model model) {
    ModelAndView modelAndView = new ModelAndView("users/sign_up");

//    model.addAttribute("userForm", new User());
    return modelAndView;
  }

  @RequestMapping(value = "/sign_up", method = RequestMethod.POST)
  public ModelAndView signUp(@ModelAttribute("signUpForm") User user, BindingResult bindingResult) {
    ModelAndView modelAndView = new ModelAndView();
    userValidator.validate(user, bindingResult);

    if (bindingResult.hasErrors()) {
      modelAndView.setViewName("users/sign_up");
      return modelAndView;
    }

    userService.create(user);
    modelAndView.setViewName("redirect:/users/sign_in");
    return modelAndView;
  }

  @RequestMapping(value = "/sign_in", method = RequestMethod.GET)
  public ModelAndView signIn(@RequestParam(value = "error", required = false) String error,
                             @RequestParam(value = "logout", required = false) String logout) {
    ModelAndView model = new ModelAndView();
    if (error != null) {
      model.addObject("error", "Invalid username and password!");
    }

    if (logout != null) {
      model.addObject("message", "You've been logged out successfully.");
    }
    model.setViewName("users/sign_in");

    return model;
  }

//  @RequestMapping(value = "/sign_in", method = RequestMethod.POST)
//  public ModelAndView singIn(@ModelAttribute("signInForm") User user, BindingResult bindingResult) {
//    try {
//      securityService.login(user.getUsername(), user.getPassword());
//    } catch (BadCredentialsException e) {
//      return new ModelAndView("users/sign_in");
//    }
//    return new ModelAndView("home");
//  }

//  @RequestMapping(method = RequestMethod.GET)
//  public ResponseEntity<Collection<User>> getAllUsers(){
//    return new ResponseEntity<>((Collection<User>) repository.findAll(), HttpStatus.OK);
//  }
}
