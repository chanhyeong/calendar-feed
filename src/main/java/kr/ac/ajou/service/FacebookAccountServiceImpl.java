package kr.ac.ajou.service;

import kr.ac.ajou.model.FacebookAccount;
import kr.ac.ajou.model.User;
import kr.ac.ajou.respoitory.FacebookAccountRepository;
import kr.ac.ajou.respoitory.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by huy on 2016. 12. 10..
 */
@Service
public class FacebookAccountServiceImpl implements FacebookAccountService {

  @Autowired
  private FacebookAccountRepository facebookAccountRepository;

  @Override
  public void create(FacebookAccount facebookAccount) {
    facebookAccountRepository.save(facebookAccount);
  }

  @Override
  public void save(FacebookAccount facebookAccount) {
    facebookAccountRepository.save(facebookAccount);
  }

  @Override
  public void findOrCreate(String fid, String accessToken, User user) {
    FacebookAccount facebookAccount = facebookAccountRepository.getByFid(fid);
    if (facebookAccount == null) {
      facebookAccount = new FacebookAccount(fid, accessToken, user);
    }
    facebookAccount.setAccessToken(accessToken);
    facebookAccount.setUser(user);
    facebookAccountRepository.save(facebookAccount);
  }

  @Override
  public FacebookAccount findByFid(String fid) {
    return facebookAccountRepository.getByFid(fid);
  }
}
