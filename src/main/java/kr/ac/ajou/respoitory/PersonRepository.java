package kr.ac.ajou.respoitory;

/**
 * Created by huy on 2016. 11. 17..
 */
import java.util.List;

import kr.ac.ajou.model.Person;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "people", path = "people")
public interface PersonRepository extends PagingAndSortingRepository<Person, Long> {

  List<Person> findByLastName(@Param("name") String name);

}