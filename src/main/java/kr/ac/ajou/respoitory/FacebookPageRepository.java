package kr.ac.ajou.respoitory;

import kr.ac.ajou.model.FacebookAccount;
import kr.ac.ajou.model.FacebookPage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "facebook_pages", path = "facebook_pages")
public interface FacebookPageRepository extends JpaRepository<FacebookPage, Long> {
  FacebookPage getByName(String name);
  FacebookPage getByFid(String fid);
  FacebookPage getByUrl(String url);
}
