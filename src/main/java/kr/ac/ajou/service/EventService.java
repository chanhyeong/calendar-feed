package kr.ac.ajou.service;

import kr.ac.ajou.model.Event;

/**
 * Created by huy on 2016. 12. 11..
 */
public interface EventService {
  Event findOrCreate(Event event);
}
