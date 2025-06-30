package aivlemini.domain;

import aivlemini.domain.*;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

//<<< PoEAA / Repository
@RepositoryRestResource(collectionResourceRel = "userInfos", path = "userInfos")
public interface UserInfoRepository
    extends PagingAndSortingRepository<UserInfo, Long> {}
