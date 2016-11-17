package kr.ac.ajou.controller;

import kr.ac.ajou.model.Person;
import kr.ac.ajou.respoitory.PersonRepository;
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
@RequestMapping("/persons")
public class PersonRestController {

  @Autowired
  private PersonRepository repository;

  @RequestMapping(method = RequestMethod.GET)
  public ResponseEntity<Collection<Person>> getAllPeople(){
    return new ResponseEntity<>((Collection<Person>) repository.findAll(), HttpStatus.OK);
  }
}
