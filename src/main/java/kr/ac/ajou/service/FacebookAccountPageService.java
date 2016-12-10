package kr.ac.ajou.service;

import kr.ac.ajou.model.FacebookAccount;
import kr.ac.ajou.model.FacebookPage;

/**
 * Created by huy on 2016. 12. 10..
 */
public interface FacebookAccountPageService {
  void create(FacebookAccount facebookAccount, FacebookPage facebookPage);
}
