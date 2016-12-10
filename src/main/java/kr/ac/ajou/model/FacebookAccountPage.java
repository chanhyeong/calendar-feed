//package kr.ac.ajou.model;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.FetchType;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.OneToOne;
//import javax.persistence.Table;
//
//@Entity
//@Table(name="ACCOUNT_PAGE")
//public class FacebookAccountPage {
//
//	@Id
//	@OneToOne(fetch=FetchType.LAZY)
//	@JoinColumn(name="FACEBOOK_ACCOUNT_ID")
//	@Column(name="ACCOUNT_ID")
//	private long accountId;
//
//	@Id
//	@OneToOne(fetch=FetchType.LAZY)
//	@JoinColumn(name="FACEBOOK_PAGE_ID")
//	@Column(name="PAGE_ID")
//	private long pageId;
//
//
//
//	public FacebookAccountPage(long accountId, long pageId) {
//		super();
//		this.accountId = accountId;
//		this.pageId = pageId;
//	}
//
//	public long getAccount_id() {
//		return accountId;
//	}
//
//	public void setAccount_id(int accountId) {
//		this.accountId = accountId;
//	}
//
//	public long getPage_id() {
//		return pageId;
//	}
//
//	public void setPage_id(int pageId) {
//		this.pageId = pageId;
//	}
//
//
//}
