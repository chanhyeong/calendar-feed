package kr.ac.ajou.model;

import org.springframework.social.facebook.api.Facebook;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="facebook_accounts", uniqueConstraints = {
    @UniqueConstraint(columnNames = "fid")})
public class FacebookAccount {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String fid;
  private String accessToken;

  @OneToOne
  private User user;

  @ManyToMany
  @JoinTable(
      name="facebook_accounts_pages",
      joinColumns=@JoinColumn(name="facebook_account_id", referencedColumnName="id"),
      inverseJoinColumns=@JoinColumn(name="facebook_page_id", referencedColumnName="id"))
  private List<FacebookPage> facebookPages;

  public FacebookAccount() {
  }

  public FacebookAccount(String fId, String accessToken, User user) {
    this.fid = fId;
    this.accessToken = accessToken;
    this.user = user;
  }

  public String getFid() {
    return fid;
  }

  public List<FacebookPage> getFacebookPages() {
    return facebookPages;
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
