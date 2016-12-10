package kr.ac.ajou.model;

import javax.persistence.*;

@Entity
@Table(name="facebook_accounts_pages", uniqueConstraints = {
    @UniqueConstraint(columnNames = {
        "facebook_account_id",
        "facebook_page_id"
    })
})
public class FacebookAccountPage {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @PrimaryKeyJoinColumn
  private FacebookAccount facebookAccount;

  @ManyToOne(fetch = FetchType.LAZY)
  @PrimaryKeyJoinColumn
  private FacebookPage facebookPage;

  public FacebookAccountPage(FacebookAccount facebookAccount, FacebookPage facebookPage) {
    this.facebookAccount = facebookAccount;
    this.facebookPage = facebookPage;
  }

  public FacebookAccountPage() {

  }
}
