package kr.ac.ajou.controller;

import kr.ac.ajou.model.Event;
import kr.ac.ajou.model.FacebookPage;
import kr.ac.ajou.model.User;
import kr.ac.ajou.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.Collections;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * Created by huy on 2016. 12. 11..
 */
@RestController
@RequestMapping("/events")
public class EventController {
  @Autowired
  private UserService userService;

  @RequestMapping(value = "/", method = RequestMethod.GET)
  public ModelAndView events(Principal principal) {
    ModelAndView modelAndView = new ModelAndView("/events/index");
    User user = userService.find(principal);
    List<Event> events = user.getFacebookAccount()
        .getFacebookPages()
        .stream()
        .flatMap(facebookPage -> facebookPage.getEvents().stream())
        .collect(Collectors.toCollection(TreeSet::new))
        .stream().collect(Collectors.toList());
    Collections.reverse(events);
    modelAndView.addObject("events", events);
    return modelAndView;
  }
}
