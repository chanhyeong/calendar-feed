package kr.ac.ajou.respoitory;

import java.util.Date;
import java.util.List;

import kr.ac.ajou.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "events", path = "events")
public interface EventRepository extends JpaRepository<Event, Long>{
  Event getByFid(String fid);
}
