package aivlemini.domain;

import aivlemini.ReadserviceApplication;
import aivlemini.domain.ReadApplied;
import aivlemini.domain.ReadFailed;
import aivlemini.domain.ReadSucceed;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Reading_table")
@Data
//<<< DDD / Aggregate Root
public class Reading {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Boolean isPurchase;

    private String statusMessage;

    private Long userId;

    private Long bookId;

    @PostPersist
    public void onPostPersist() {
        ReadApplied readApplied = new ReadApplied(this);
        readApplied.publishAfterCommit();

        ReadFailed readFailed = new ReadFailed(this);
        readFailed.publishAfterCommit();

        ReadSucceed readSucceed = new ReadSucceed(this);
        readSucceed.publishAfterCommit();
    }

    public static ReadingRepository repository() {
        ReadingRepository readingRepository = ReadserviceApplication.applicationContext.getBean(
            ReadingRepository.class
        );
        return readingRepository;
    }

    //<<< Clean Arch / Port Method
    public static void readFail(OutOfPoint outOfPoint) {
        //implement business logic here:

        /** Example 1:  new item 
        Reading reading = new Reading();
        repository().save(reading);

        ReadFailed readFailed = new ReadFailed(reading);
        readFailed.publishAfterCommit();
        */

        /** Example 2:  finding and process
        
        // if outOfPoint.readingIduserInfoId exists, use it
        
        // ObjectMapper mapper = new ObjectMapper();
        // Map<Long, Object> pointMap = mapper.convertValue(outOfPoint.getReadingId(), Map.class);
        // Map<Long, Object> pointMap = mapper.convertValue(outOfPoint.getUserInfoId(), Map.class);

        repository().findById(outOfPoint.get???()).ifPresent(reading->{
            
            reading // do something
            repository().save(reading);

            ReadFailed readFailed = new ReadFailed(reading);
            readFailed.publishAfterCommit();

         });
        */

    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public static void readSuccess(PointDecreased pointDecreased) {
        //implement business logic here:

        /** Example 1:  new item 
        Reading reading = new Reading();
        repository().save(reading);

        ReadSucceed readSucceed = new ReadSucceed(reading);
        readSucceed.publishAfterCommit();
        */

        /** Example 2:  finding and process
        
        // if pointDecreased.readingIduserInfoId exists, use it
        
        // ObjectMapper mapper = new ObjectMapper();
        // Map<Long, Object> pointMap = mapper.convertValue(pointDecreased.getReadingId(), Map.class);
        // Map<Long, Object> pointMap = mapper.convertValue(pointDecreased.getUserInfoId(), Map.class);

        repository().findById(pointDecreased.get???()).ifPresent(reading->{
            
            reading // do something
            repository().save(reading);

            ReadSucceed readSucceed = new ReadSucceed(reading);
            readSucceed.publishAfterCommit();

         });
        */

    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
