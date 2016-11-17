package kr.ac.ajou.model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by huy on 2016. 11. 17..
 */
@Entity
@Data
@Table(name = "users")
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  private String email;
  private String username;
  private String name;
  private String role;
  private String password;
  private String passwordConfirm;

  private Timestamp lastSignInAt;
  private Timestamp createdAt;
  private Timestamp updatedAt;
}
