package kr.ac.ajou.service;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import kr.ac.ajou.model.Event;
import kr.ac.ajou.model.FacebookAccount;
import kr.ac.ajou.model.FacebookPage;
import kr.ac.ajou.respoitory.EventRepository;
import kr.ac.ajou.respoitory.FacebookPageRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.impl.FacebookTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by huy on 2016. 12. 10..
 */
@Service
public class FacebookPageSerivceImpl implements FacebookPageService {
  public static final String FACEBOOK_PAGE_GET_URL = "https://graph.facebook.com/v2.8";
  public static final String FACEBOOK_PAGE_EVENTS_GET_URL = "https://graph.facebook.com/v2.8/%s/events";

  @Autowired
  FacebookPageRepository facebookPageRepository;

  @Autowired
  EventService eventService;

  public FacebookPage findById(Long id) {
    return facebookPageRepository.findOne(id);
  }

  public FacebookPage findOrCreateByUrl(String url) {
    FacebookPage facebookPage = facebookPageRepository.getByUrl(url);
    if (facebookPage == null) {
      facebookPage = new FacebookPage();
      facebookPage.setUrl(url);
      facebookPage.setFid(getPageFid(url));
      facebookPageRepository.save(facebookPage);
    }
    return facebookPage;
  }

  public FacebookPage update(FacebookPage facebookPage, Facebook facebook) {
    String json = facebook.restOperations().getForObject(
        String.format("%s/%s", FACEBOOK_PAGE_GET_URL, facebookPage.getFid()),
        String.class
    );
    Map<String, Object> map = new HashMap<String, Object>();
    ObjectMapper mapper = new ObjectMapper();
    try {
      map = mapper.readValue(json, new TypeReference<Map<String, String>>(){});
    } catch (IOException e) {
      e.printStackTrace();
    }
    facebookPage.setName((String)map.get("name"));

    return facebookPageRepository.save(facebookPage);

  }


  private String getPageFid(String url) {
    String fid = null;
    RestTemplate rest = new RestTemplate();
    String body = rest.getForObject(url, String.class);
    Matcher matcher = Pattern.compile("\\?page_id=(\\d+)").matcher(body);
    if(matcher.find()) {
      String[] tokens = matcher.group().split("=");
      fid = tokens[1];
    }

    return fid;
  }

  public List<Event> updateEvents(FacebookPage facebookPage, Facebook facebook) {
    List<Event> events = new ArrayList<Event>();
    String jsonString = facebook.restOperations().getForObject(
        String.format(FACEBOOK_PAGE_EVENTS_GET_URL, facebookPage.getFid()),
        String.class
    );
    JSONObject jsonObject = new JSONObject(jsonString);

    JSONArray jsonArray = jsonObject.getJSONArray("data");
    for (int i = 0; i < jsonArray.length(); i++) {
      JSONObject jsonEvent = (JSONObject) jsonArray.get(i);
      Event event = new Event(
          getString(jsonEvent, "id"),
          getString(jsonEvent, "name"),
          getString(getJSONObject(jsonEvent, "place"), "name"),
          getString(jsonEvent, "description"),
          getString(jsonEvent, "start_time"),
          getString(jsonEvent, "end_time"),
          facebookPage
      );
      events.add(eventService.findOrCreate(event));
    }
    return events;
  }

  private String getString(JSONObject obj, String key) {
    try {
      return obj.getString(key);
    } catch (Exception e) {
      e.printStackTrace();
    }

    return null;
  }

  private JSONObject getJSONObject(JSONObject obj, String key) {
    try {
      return obj.getJSONObject(key);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }
}
