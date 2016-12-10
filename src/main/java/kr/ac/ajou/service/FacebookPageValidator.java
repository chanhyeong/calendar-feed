package kr.ac.ajou.service;

import kr.ac.ajou.model.FacebookPage;
import kr.ac.ajou.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Created by huy on 2016. 12. 10..
 */
@Component
public class FacebookPageValidator implements Validator {
  public boolean supports(Class<?> aClass) {
    return User.class.equals(aClass);
  }

  public void validate(Object o, Errors errors) {
    FacebookPage facebookPage = (FacebookPage) o;
    // Validate facebook page url.
//    if (facebookPage.) {
//      errors.rejectValue("url", "");
//    }
  }
}
