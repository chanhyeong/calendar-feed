package kr.ac.ajou.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import kr.ac.ajou.model.User;
import kr.ac.ajou.service.FacebookAccountService;
import kr.ac.ajou.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.support.OAuth2ConnectionFactory;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.impl.FacebookTemplate;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by chanhyeong on 2016-12-08.
 */

@RestController
@RequestMapping("/social")
public class SocialController {
  public static final String FACEBOOK_URL = "https://www.facebook.com/v2.8/dialog/oauth";
  public static final String FACEBOOK_EXTENSION_URL = "https://graph.facebook.com/v2.8/oauth/access_token";

  @Autowired
  private UserService userService;

  @Autowired
  private FacebookAccountService facebookAccountService;

  private Environment env;
  private final ConnectionFactoryLocator connectionFactoryLocator;

  public SocialController(Environment env, ConnectionFactoryLocator connectionFactoryLocator) {
    this.env = env;
    this.connectionFactoryLocator = connectionFactoryLocator;
  }

  @RequestMapping(value = "/{providerId}/connected", method = RequestMethod.GET)
  public ModelAndView connected(@PathVariable String providerId, String code, Principal principal) {
    ModelAndView modelAndView = new ModelAndView();
    if (providerId.equals("facebook")) {
      OAuth2ConnectionFactory<?> connectionFactory =
          (OAuth2ConnectionFactory<?>) connectionFactoryLocator.getConnectionFactory(providerId);
      AccessGrant accessGrant = connectionFactory
          .getOAuthOperations()
          .exchangeForAccess(
              code,
              env.getProperty("spring.social.facebook.redirectUri"),
              null);

      String extensionAccessToken = accessTokenExpireExtension(accessGrant);
      User user = userService.findByUsername(principal.getName());
      facebookAccountService.findOrCreate(getFid(extensionAccessToken), extensionAccessToken, user);
      modelAndView.setViewName("redirect:/welcome");
    }

    return modelAndView;
  }

  @RequestMapping(value = "/{providerId}/connect", method = RequestMethod.POST)
  public ModelAndView connect(@PathVariable String providerId, NativeWebRequest request) {
    ModelAndView modelAndView = new ModelAndView();
    if (providerId.equals("facebook")) {
      modelAndView.setViewName(String.format("redirect:%s?%s", FACEBOOK_URL, connectFacebookParams()));
    }

    return modelAndView;
  }

  private String connectFacebookParams() {
    StringBuilder params = new StringBuilder();
    params.append(String.format("client_id=%s&", env.getProperty("spring.social.facebook.appId")));
    params.append(String.format("redirect_uri=%s&", env.getProperty("spring.social.facebook.redirectUri")));
    params.append("response_type=code&");
    return params.toString();
  }

  private String extensionFacebookParams(String accessToken) {
    StringBuilder params = new StringBuilder();
    params.append("grant_type=fb_exchange_token&");
    params.append(String.format("client_id=%s&", env.getProperty("spring.social.facebook.appId")));
    params.append(String.format("client_secret=%s&", env.getProperty("spring.social.facebook.appSecret")));
    params.append(String.format("fb_exchange_token=%s&", accessToken));
    return params.toString();
  }

  private String getFid(String accessToken) {
    Facebook facebook = new FacebookTemplate(accessToken);
    return facebook.userOperations().getUserProfile().getId();
  }

  private String accessTokenExpireExtension(AccessGrant accessGrant) {
    RestTemplate rest = new RestTemplate();
    String json = rest.getForObject(
        String.format("%s?%s", FACEBOOK_EXTENSION_URL, extensionFacebookParams(accessGrant.getAccessToken())),
        String.class);
    Map<String, Object> map = new HashMap<String, Object>();
    ObjectMapper mapper = new ObjectMapper();
    try {
      map = mapper.readValue(json, new TypeReference<Map<String, String>>(){});
    } catch (IOException e) {
      e.printStackTrace();
    }

    return (String)map.get("access_token");
  }
}
