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

  @RequestMapping(value = "/registration", method = RequestMethod.GET)
  public ModelAndView registration(Model model) {
    ModelAndView modelAndView = new ModelAndView("users/registration");

//    model.addAttribute("userForm", new User());
    return modelAndView;
  }

  @RequestMapping(value = "/registration", method = RequestMethod.POST)
  public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {
    userValidator.validate(userForm, bindingResult);

    if (bindingResult.hasErrors()) {
      return "users/registration";
    }

    userService.save(userForm);

    securityService.autologin(userForm.getUsername(), userForm.getPasswordConfirm());

    return "redirect:/welcome";
  }

  @RequestMapping(value = "/login", method = RequestMethod.GET)
  public String login(Model model, String error, String logout) {
    if (error != null)
      model.addAttribute("error", "Your username and password is invalid.");

    if (logout != null)
      model.addAttribute("message", "You have been logged out successfully.");

    return "users/login";
  }

//  @RequestMapping(method = RequestMethod.GET)
//  public ResponseEntity<Collection<User>> getAllUsers(){
//    return new ResponseEntity<>((Collection<User>) repository.findAll(), HttpStatus.OK);
//  }
}

