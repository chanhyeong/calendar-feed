package kr.ac.ajou.respoitory;

import kr.ac.ajou.model.Person;
import kr.ac.ajou.model.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

/**
 * Created by huy on 2016. 11. 17..
 */

@RepositoryRestResource(collectionResourceRel = "users", path = "users")
public interface UserRepository extends PagingAndSortingRepository<User, Long> {

  List<User> findByName(@Param("name") String name);
}