package kr.ac.ajou.controller;

import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

/**
 * Created by chanhyeong on 2016-12-08.
 */

@EnableOAuth2Client
@RestController
@RequestMapping("/social")
public class SocialController {
    @RequestMapping("/user")
    public Principal user(Principal principal) {
        return principal;
    }

    @RequestMapping(value = "/social", method = RequestMethod.GET)
    public ModelAndView signIn(@RequestParam(value = "error", required = false) String error,
                               @RequestParam(value = "logout", required = false) String logout) {
        ModelAndView model = new ModelAndView();

        model.setViewName("social/facebook");

        return model;
    }
}
