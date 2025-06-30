package aivlemini.domain;

import aivlemini.UserserviceApplication;
import aivlemini.domain.UserRegistered;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "UserInfo_table")
@Data
//<<< DDD / Aggregate Root
public class UserInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String userName;

    private Boolean isPurchase;

    private Date planStartDate;

    private Date planEndDate;

    private String loginId;

    private String loginPw;

    @PostPersist
    public void onPostPersist() {
        UserRegistered userRegistered = new UserRegistered(this);
        userRegistered.publishAfterCommit();
    }

    public static UserInfoRepository repository() {
        UserInfoRepository userInfoRepository = UserserviceApplication.applicationContext.getBean(
            UserInfoRepository.class
        );
        return userInfoRepository;
    }

    //<<< Clean Arch / Port Method
    public void buyPlan(BuyPlanCommand buyPlanCommand) {
        //implement business logic here:

        PlanBought planBought = new PlanBought(this);
        planBought.publishAfterCommit();
    }

    //>>> Clean Arch / Port Method

    //<<< Clean Arch / Port Method
    public static void guidePlan(ReadFailed readFailed) {
        //implement business logic here:

        /** Example 1:  new item 
        UserInfo userInfo = new UserInfo();
        repository().save(userInfo);

        */

        /** Example 2:  finding and process
        
        // if readFailed.userInfoId exists, use it
        
        // ObjectMapper mapper = new ObjectMapper();
        // Map<Long, Object> readingMap = mapper.convertValue(readFailed.getUserInfoId(), Map.class);

        repository().findById(readFailed.get???()).ifPresent(userInfo->{
            
            userInfo // do something
            repository().save(userInfo);


         });
        */

    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
