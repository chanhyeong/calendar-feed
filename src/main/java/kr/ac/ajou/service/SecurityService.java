package kr.ac.ajou.service;

/**
 * Created by huy on 2016. 11. 17..
 */
public interface SecurityService {
  String findLoggedInUsername();
  void autologin(String username, String password);
}
