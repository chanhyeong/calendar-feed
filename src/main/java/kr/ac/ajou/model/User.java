package kr.ac.ajou.model;


import org.springframework.social.facebook.api.Facebook;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by huy on 2016. 11. 17..
 */
@Entity
@Table(name = "users")
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

//  private String email;
  private String username;
  private String name;
  private String role;
  private String password;
  private String passwordConfirm;

  private Timestamp lastSignInAt;
  private Timestamp createdAt;
  private Timestamp updatedAt;

  @OneToOne(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL)
  private FacebookAccount facebookAccount;

  public long getId() {
    return id;
  }

  public String getUsername() {
    return username;
  }

  public String getName() {
    return name;
  }

  public String getRole() {
    return role;
  }

  public String getPassword() {
    return password;
  }

  public String getPasswordConfirm() {
    return passwordConfirm;
  }

  public Timestamp getLastSignInAt() {
    return lastSignInAt;
  }

  public Timestamp getCreatedAt() {
    return createdAt;
  }

  public Timestamp getUpdatedAt() {
    return updatedAt;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setRole(String role) {
    this.role = role;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public void setPasswordConfirm(String passwordConfirm) {
    this.passwordConfirm = passwordConfirm;
  }

  public void setLastSignInAt(Timestamp lastSignInAt) {
    this.lastSignInAt = lastSignInAt;
  }

  public void setCreatedAt(Timestamp createdAt) {
    this.createdAt = createdAt;
  }

  public void setUpdatedAt(Timestamp updatedAt) {
    this.updatedAt = updatedAt;
  }
}
