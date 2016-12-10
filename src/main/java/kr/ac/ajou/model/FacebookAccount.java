package kr.ac.ajou.model;

import org.springframework.social.facebook.api.Facebook;

import javax.persistence.*;

@Entity
@Table(name="facebook_accounts", uniqueConstraints = {
    @UniqueConstraint(columnNames = "fid")})
public class FacebookAccount {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String fid;
  private String accessToken;

  @OneToOne(fetch = FetchType.LAZY)
  @PrimaryKeyJoinColumn
  private User user;

  public FacebookAccount() {
  }

  public FacebookAccount(String fId, String accessToken, User user) {
    this.fid = fId;
    this.accessToken = accessToken;
    this.user = user;
  }

  public String getfId() {
    return fid;
  }

  public void setfId(String fId) {
    this.fid = fId;
  }

  public String getAccessToken() {
    return accessToken;
  }

  public void setAccessToken(String accessToken) {
    this.accessToken = accessToken;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public long getId() {
    return id;
  }
}
