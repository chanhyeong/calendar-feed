//package kr.ac.ajou.model;
//
//import java.util.Date;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.FetchType;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.OneToOne;
//import javax.persistence.Table;
//import javax.persistence.Temporal;
//import javax.persistence.TemporalType;
//
//@Entity
//@Table(name="EVENT")
//public class Event {
//
//	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
//	@Column(name="EVENT_ID")
//	private long id;
//
//	private String name;
//	@Temporal(TemporalType.DATE)
//	private Date date;
//	private String location;
//	private String description;
//
//	@OneToOne(fetch=FetchType.LAZY)
//	@JoinColumn(name = "FACEBOOK_PAGE_ID")
//	private FacebookPage pageId;
//
//
//	public Event(String name, java.util.Date date2, String location) {
//		super();
//		this.name = name;
//		this.date = date2;
//		this.location = location;
//	}
//
//	public Event(String name, Date date, String location, FacebookPage pageId) {
//		super();
//		this.name = name;
//		this.date = date;
//		this.location = location;
//		this.pageId = pageId;
//	}
//
//	public Event(long id, String name, Date date, String location, String description, FacebookPage pageId) {
//		super();
//		this.id = id;
//		this.name = name;
//		this.date = date;
//		this.location = location;
//		this.description = description;
//		this.pageId = pageId;
//	}
//
//	public long getId() {
//		return id;
//	}
//
//	public void setId(int id) {
//		this.id = id;
//	}
//
//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}
//
//	public Date getDate() {
//		return date;
//	}
//
//	public void setDate(Date date) {
//		this.date = date;
//	}
//
//	public String getLocation() {
//		return location;
//	}
//
//	public void setLocation(String location) {
//		this.location = location;
//	}
//
//	public String getDescription() {
//		return description;
//	}
//
//	public void setDescription(String description) {
//		this.description = description;
//	}
//
//	public FacebookPage getPage_id() {
//		return pageId;
//	}
//
//	public void setPage_id(FacebookPage pageId) {
//		this.pageId = pageId;
//	}
//}
