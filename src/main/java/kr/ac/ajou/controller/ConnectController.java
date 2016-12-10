package kr.ac.ajou.controller;

import kr.ac.ajou.interceptor.FacebookConnectionInterceptor;
import org.springframework.social.connect.ConnectionFactory;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.view.RedirectView;

/**
 * Created by huy on 2016. 12. 9..
 */
@Controller
@RequestMapping("/connect")
public class ConnectController extends org.springframework.social.connect.web.ConnectController {

  public ConnectController(ConnectionFactoryLocator connectionFactoryLocator, ConnectionRepository connectionRepository) {
    super(connectionFactoryLocator, connectionRepository);
    addInterceptor(new FacebookConnectionInterceptor());
  }

  /**
   * Process a connect form submission by commencing the process of establishing a connection to the provider on behalf of the member.
   * For OAuth1, fetches a new request token from the provider, temporarily stores it in the session, then redirects the member to the provider's site for authorization.
   * For OAuth2, redirects the user to the provider's site for authorization.
   * @param providerId the provider ID to connect to
   * @param request the request
   * @return a RedirectView to the provider's authorization page or to the connection status page if there is an error
   */
//  @RequestMapping(value="/{providerId}", method= RequestMethod.POST)
//  public RedirectView connect(@PathVariable String providerId, NativeWebRequest request) {
//    ConnectionFactory<?> connectionFactory = connectionFactoryLocator.getConnectionFactory(providerId);
//    MultiValueMap<String, String> parameters = new LinkedMultiValueMap<String, String>();
//    preConnect(connectionFactory, parameters, request);
//    try {
//      return new RedirectView(connectSupport.buildOAuthUrl(connectionFactory, request, parameters));
//    } catch (Exception e) {
//      sessionStrategy.setAttribute(request, PROVIDER_ERROR_ATTRIBUTE, e);
//      return connectionStatusRedirect(providerId, request);
//    }
//  }
}
