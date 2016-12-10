package kr.ac.ajou.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="facebook_pages", uniqueConstraints = {
    @UniqueConstraint(columnNames = "fid")
})
public class FacebookPage {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

  private String fid;
	private String name;
	private String url;

	public FacebookPage() {
	}

  @OneToMany(fetch = FetchType.LAZY, mappedBy="facebookPage")
  private List<Event> events;

  public List<Event> getEvents() {
    return events;
  }

  public void setEvents(List<Event> events) {
    this.events = events;
  }

  public String getFid() {
    return fid;
  }

  public void setFid(String fid) {
    this.fid = fid;
  }

  public long getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
