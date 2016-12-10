package kr.ac.ajou.service;

import kr.ac.ajou.model.FacebookAccount;
import kr.ac.ajou.model.FacebookPage;
import kr.ac.ajou.respoitory.FacebookPageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.impl.FacebookTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by huy on 2016. 12. 10..
 */
@Service
public class FacebookPageSerivceImpl implements FacebookPageService {

  @Autowired
  FacebookPageRepository facebookPageRepository;

  @Override
  public FacebookPage findOrCreateByUrl(String url) {
    FacebookPage facebookPage = facebookPageRepository.getByUrl(url);
    if (facebookPage == null) {
      facebookPage = new FacebookPage();
      facebookPage.setUrl(url);
      facebookPage.setFid(getPageFid(url));
      facebookPageRepository.save(facebookPage);
    }
    return facebookPage;
  }

  @Override
  public FacebookPage update(FacebookPage facebookPage) {
    return facebookPageRepository.save(facebookPage);
  }


  private String getPageFid(String url) {
    String fid = null;
    RestTemplate rest = new RestTemplate();
    String body = rest.getForObject(url, String.class);
    Matcher matcher = Pattern.compile("\\?page_id=(\\d+)").matcher(body);
    if(matcher.find()) {
      String[] tokens = matcher.group().split("=");
      fid = tokens[1];
    }

    return fid;
  }
}
