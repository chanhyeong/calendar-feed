package kr.ac.ajou.service;

import kr.ac.ajou.model.User;
import kr.ac.ajou.respoitory.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
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

  public void save(User user) {
    long now = ZonedDateTime.now(ZoneId.of("Asia/Seoul")).toInstant().toEpochMilli();
    user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
    user.setPasswordConfirm(bCryptPasswordEncoder.encode(user.getPasswordConfirm()));
    user.setUpdatedAt(new Timestamp(now));
    userRepository.save(user);
  }


  public void create(User user) {
    long now = ZonedDateTime.now(ZoneId.of("Asia/Seoul")).toInstant().toEpochMilli();
    user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
    user.setPasswordConfirm(bCryptPasswordEncoder.encode(user.getPasswordConfirm()));
    user.setUpdatedAt(new Timestamp(now));
    user.setCreatedAt(new Timestamp(now));
    userRepository.save(user);
  }

  public User findByUsername(String username) {
    return userRepository.findByUsername(username);
  }

  public User find(Principal principal) {
    return findByUsername(principal.getName());
  }
}
