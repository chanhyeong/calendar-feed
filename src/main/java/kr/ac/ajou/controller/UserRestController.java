package kr.ac.ajou.controller;

import kr.ac.ajou.model.Person;
import kr.ac.ajou.model.User;
import kr.ac.ajou.respoitory.PersonRepository;
import kr.ac.ajou.respoitory.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

/**
 * Created by huy on 2016. 11. 17..
 */
@RestController
@RequestMapping("/users")
public class UserRestController {

  @Autowired
  private UserRepository repository;

  @RequestMapping(method = RequestMethod.GET)
  public ResponseEntity<Collection<User>> getAllUsers(){
//    (Collection<Person>) repository.findByLastName()
    return new ResponseEntity<>((Collection<User>) repository.findAll(), HttpStatus.OK);
  }
}

