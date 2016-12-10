package kr.ac.ajou.service;

import kr.ac.ajou.model.FacebookAccount;
import kr.ac.ajou.model.User;

/**
 * Created by huy on 2016. 12. 10..
 */
public interface FacebookAccountService {
  void findOrCreate(String fid, String accessToken, User user);
  void create(FacebookAccount facebookAccount);
  void save(FacebookAccount facebookAccount);

  FacebookAccount findByFid(String fid);
}
