package kr.ac.ajou.respoitory;

import java.util.List;

import kr.ac.ajou.model.FacebookAccountPage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@RepositoryRestResource(collectionResourceRel = "facebook_accounts_pages", path = "facebook_accounts_pages")
public interface FacebookAccountPageRepository extends JpaRepository<FacebookAccountPage, Long>{

}
