package kr.ac.ajou.service;

import kr.ac.ajou.model.Event;
import kr.ac.ajou.respoitory.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by huy on 2016. 12. 11..
 */
@Service
public class EventServiceImpl implements EventService {

  @Autowired
  private EventRepository eventRepository;

  public Event findOrCreate(Event event) {
    Event res = eventRepository.getByFid(event.getFid());
    if (res == null) {
      res = eventRepository.save(event);
    }
    return res;
  }
}
