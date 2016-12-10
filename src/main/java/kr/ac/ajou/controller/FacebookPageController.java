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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
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
  public static final String FACEBOOK_PAGE_GET_URL = "https://graph.facebook.com/v2.8";

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
      String json = facebook.restOperations().getForObject(
        String.format("%s/%s", FACEBOOK_PAGE_GET_URL, page.getFid()),
        String.class
      );
      Map<String, Object> map = new HashMap<String, Object>();
      ObjectMapper mapper = new ObjectMapper();
      try {
        map = mapper.readValue(json, new TypeReference<Map<String, String>>(){});
      } catch (IOException e) {
        e.printStackTrace();
      }
      page.setName((String)map.get("name"));
      facebookPageService.update(page);
    }
    return modelAndView;
  }
}
