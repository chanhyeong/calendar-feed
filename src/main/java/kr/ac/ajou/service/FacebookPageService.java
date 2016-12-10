package kr.ac.ajou.service;

import kr.ac.ajou.model.FacebookAccount;
import kr.ac.ajou.model.FacebookPage;
import kr.ac.ajou.model.User;

/**
 * Created by huy on 2016. 12. 10..
 */
public interface FacebookPageService {
  FacebookPage findOrCreateByUrl(String url);
  FacebookPage update(FacebookPage facebookPage);
}
