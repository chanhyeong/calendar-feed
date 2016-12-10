package kr.ac.ajou.respoitory;

import kr.ac.ajou.model.FacebookAccount;
import kr.ac.ajou.service.FacebookAccountService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@RepositoryRestResource(collectionResourceRel = "facebook_accounts", path = "facebook_accounts")
public interface FacebookAccountRepository extends JpaRepository<FacebookAccount, Long>{

  FacebookAccount getByFid(String fid);


}
