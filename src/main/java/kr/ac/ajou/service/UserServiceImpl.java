package kr.ac.ajou.service;

import kr.ac.ajou.model.User;
import kr.ac.ajou.respoitory.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;

/**
 * Created by huy on 2016. 11. 17..
 */
@Service
public class UserServiceImpl implements UserService {
  @Autowired
  private UserRepository userRepository;

  @Autowired
  private BCryptPasswordEncoder bCryptPasswordEncoder;

  @Override
  public void save(User user) {
    user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
    user.setPasswordConfirm(bCryptPasswordEncoder.encode(user.getPasswordConfirm()));
    userRepository.save(user);
  }

  @Override
  public User findByUsername(String username) {
    return userRepository.findByUsername(username);
  }
}
