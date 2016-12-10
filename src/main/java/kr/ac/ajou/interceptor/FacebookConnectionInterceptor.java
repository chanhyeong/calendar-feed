package kr.ac.ajou.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionFactory;
import org.springframework.social.connect.support.OAuth2Connection;
import org.springframework.social.connect.web.ConnectInterceptor;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.request.WebRequest;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huy on 2016. 12. 9..
 */
@Component
public class FacebookConnectionInterceptor implements ConnectInterceptor<Facebook> {

  @Override
  public void preConnect(ConnectionFactory<Facebook> connectionFactory, MultiValueMap<String, String> parameters, WebRequest request) {
    List<String> uris = new ArrayList<String>();
    uris.add("http://localhost:8080/social/facebook/connected");
    parameters.put("redirect_uri", uris);
    System.out.println(parameters);
  }

  @Override
  public void postConnect(Connection<Facebook> connection, WebRequest request) {
  }
}
