package kr.ac.ajou.service;

import kr.ac.ajou.model.Event;
import kr.ac.ajou.model.FacebookAccount;
import kr.ac.ajou.model.FacebookPage;
import kr.ac.ajou.model.User;
import org.springframework.social.facebook.api.Facebook;

import java.util.List;

/**
 * Created by huy on 2016. 12. 10..
 */
public interface FacebookPageService {
  FacebookPage findById(Long id);
  FacebookPage findOrCreateByUrl(String url);
  FacebookPage update(FacebookPage facebookPage, Facebook facebook);
  List<Event> updateEvents(FacebookPage facebookPage, Facebook facebook);
}
