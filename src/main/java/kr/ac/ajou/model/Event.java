package kr.ac.ajou.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.vdurmont.emoji.EmojiParser;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

import javax.persistence.*;

@Entity
@Table(name="events", uniqueConstraints = {
    @UniqueConstraint(columnNames = "fid")
})
public class Event implements Comparable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

  private String fid;
	private String name;

  private String place;

  @Lob
  private String description;

  @Temporal(TemporalType.TIMESTAMP)
	private Calendar startTime;
  @Temporal(TemporalType.TIMESTAMP)
  private Calendar endTime;

	@ManyToOne(fetch=FetchType.LAZY)
  @JoinColumn(name = "facebook_page_id", nullable = false)
	private FacebookPage facebookPage;

  public Event() {
  }

  public Event(String fid, String name, String place, String description, String startTime, String endTime, FacebookPage facebookPage) {
    this.fid = fid;
    this.name = name;
    this.place = place;
    setDescription(description);

    if (startTime != null) {
      Instant instant = Instant.from(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssZ").parse(startTime));
      this.startTime = Calendar.getInstance();
      this.startTime.setTimeInMillis(instant.toEpochMilli());
    }

    if (endTime != null) {
      Instant instant = Instant.from(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssZ").parse(endTime));
      this.endTime = Calendar.getInstance();
      this.endTime.setTimeInMillis(instant.toEpochMilli());
    }

    this.facebookPage = facebookPage;
  }

  public Event(Event event) {
    this.fid = event.getFid();
    this.name = event.getName();
    this.place = event.getPlace();
    setDescription(event.getDescription());
    this.startTime = event.getStartTime();
    this.endTime = event.getEndTime();
    this.facebookPage = event.getFacebookPage();
  }


  public long getId() {
    return id;
  }

  public String getFid() {
    return fid;
  }

  public void setFid(String fid) {
    this.fid = fid;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPlace() {
    return place;
  }

  public void setPlace(String place) {
    this.place = place;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    if (description != null) {
      this.description = EmojiParser.removeAllEmojis(description);
    }
  }

  public Calendar getStartTime() {
    return startTime;
  }

  public void setStartTime(Calendar startTime) {
    this.startTime = startTime;
  }

  public Calendar getEndTime() {
    return endTime;
  }

  public void setEndTime(Calendar endTime) {
    this.endTime = endTime;
  }

  public FacebookPage getFacebookPage() {
    return facebookPage;
  }

  public void setFacebookPage(FacebookPage facebookPage) {
    this.facebookPage = facebookPage;
  }

  public ZonedDateTime getStartTimeAsLocalDateTime() {
    if (startTime != null) {
     return ZonedDateTime.ofInstant(
          Instant.ofEpochMilli(startTime.getTimeInMillis()),
          startTime.getTimeZone().toZoneId());
    }
    return null;
  }

  public ZonedDateTime getEndTimeAsLocalDateTime() {
    if (endTime != null) {
      return ZonedDateTime.ofInstant(
          Instant.ofEpochMilli(endTime.getTimeInMillis()),
          endTime.getTimeZone().toZoneId());
    }
    return null;
  }

  public int compareTo(Object o) {
    Event that = (Event)o;
    if (this.startTime.compareTo(that.startTime) == 0) {
      return this.getFid().compareTo(that.getFid());
    }
    return this.startTime.compareTo(that.startTime);
  }


}
