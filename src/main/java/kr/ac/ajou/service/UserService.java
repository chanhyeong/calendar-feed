package kr.ac.ajou.service;

import kr.ac.ajou.model.User;

import java.security.Principal;

/**
 * Created by huy on 2016. 11. 17..
 */
public interface UserService {
  void create(User user);
  void save(User user);
  User findByUsername(String username);
  User find(Principal principal);
}
