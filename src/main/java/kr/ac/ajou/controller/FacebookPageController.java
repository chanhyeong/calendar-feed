package kr.ac.ajou.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import kr.ac.ajou.model.FacebookAccountPage;
import kr.ac.ajou.model.FacebookPage;
import kr.ac.ajou.model.User;
import kr.ac.ajou.respoitory.FacebookPageRepository;
import kr.ac.ajou.service.FacebookAccountPageService;
import kr.ac.ajou.service.FacebookPageService;
import kr.ac.ajou.service.FacebookPageValidator;
import kr.ac.ajou.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.impl.FacebookTemplate;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by huy on 2016. 12. 10..
 */
@RestController
@RequestMapping("/facebook_pages")
public class FacebookPageController {
  @Autowired
  private UserService userService;

  @Autowired
  private FacebookPageValidator facebookPageValidator;

  @Autowired
  private FacebookPageService facebookPageService;

  @Autowired
  private FacebookAccountPageService facebookAccountPageService;

  @RequestMapping(value = "/create", method = RequestMethod.POST)
  public ModelAndView createPost(
      @ModelAttribute("facebookPageForm") FacebookPage facebookPage,
      BindingResult bindingResult,
      Principal principal) {
    ModelAndView modelAndView = new ModelAndView("redirect:index");
    facebookPageValidator.validate(facebookPage, bindingResult);

    if (bindingResult.hasErrors()) {
      modelAndView.setViewName("facebook_pages/create");
    }

    User user = userService.findByUsername(principal.getName());
    facebookPage = facebookPageService.findOrCreateByUrl(facebookPage.getUrl());
    facebookAccountPageService.create(user.getFacebookAccount(), facebookPage);
    return modelAndView;
  }

  @RequestMapping(value = "/create", method = RequestMethod.GET)
  public ModelAndView createGet(Model model) {
    ModelAndView modelAndView = new ModelAndView("facebook_pages/create");

    // create
    return modelAndView;
  }

  @RequestMapping(value = "/index", method = RequestMethod.GET)
  public ModelAndView index(Principal principal) {
    ModelAndView modelAndView = new ModelAndView("facebook_pages/index");
    User user = userService.findByUsername(principal.getName());
    modelAndView.addObject("facebookPages", user.getFacebookAccount().getFacebookPages());
    return modelAndView;
  }

  @RequestMapping(value = "/update", method = RequestMethod.POST)
  public ModelAndView update(Principal principal) {
    ModelAndView modelAndView = new ModelAndView("redirect:index");
    User user = userService.find(principal);

    Facebook facebook = new FacebookTemplate(user.getFacebookAccount().getAccessToken());
    for (FacebookPage page : user.getFacebookAccount().getFacebookPages()) {
      facebookPageService.update(page, facebook);
      facebookPageService.updateEvents(page, facebook);
    }
    return modelAndView;
  }

  @RequestMapping(value = "/{facebookPageId}/events", method = RequestMethod.GET)
  public ModelAndView events(Principal principal, @PathVariable Long facebookPageId) {
    ModelAndView modelAndView = new ModelAndView("/events/index");
    FacebookPage facebookPage = facebookPageService.findById(facebookPageId);

    modelAndView.addObject("events", facebookPage.getEvents());
    return modelAndView;
  }
}
