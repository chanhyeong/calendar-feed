package kr.ac.ajou.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Timestamp;

/**
 * Created by huy on 2016. 11. 17..
 */
@Entity(name = "users")
@Data
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  private String email;
  private String name;
  private String role;

  private Timestamp lastSignInAt;
  private Timestamp createdAt;
  private Timestamp updatedAt;


}
