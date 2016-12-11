package kr.ac.ajou.service;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import kr.ac.ajou.model.FacebookAccount;
import kr.ac.ajou.model.FacebookAccountPage;
import kr.ac.ajou.model.FacebookPage;
import kr.ac.ajou.respoitory.FacebookAccountPageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by huy on 2016. 12. 10..
 */
@Service
public class FacebookAccountPageServiceImpl implements FacebookAccountPageService {

  @Autowired
  private FacebookAccountPageRepository facebookAccountPageRepository;

  public void create(FacebookAccount facebookAccount, FacebookPage facebookPage) {
    FacebookAccountPage facebookAccountPage = new FacebookAccountPage(facebookAccount, facebookPage);
    try {
      facebookAccountPageRepository.save(facebookAccountPage);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
