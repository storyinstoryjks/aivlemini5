package aivlemini.domain;

import aivlemini.domain.*;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import java.util.Date;
import java.util.List;

//<<< PoEAA / Repository
@RepositoryRestResource(collectionResourceRel = "userInfos", path = "userInfos")
public interface UserInfoRepository
    extends PagingAndSortingRepository<UserInfo, Long> {

        //요금제 만료된 사용자 조회용 커스텀 메서드
         List<UserInfo> findByPlanEndDateBeforeAndIsPurchaseTrue(Date now);
    }
