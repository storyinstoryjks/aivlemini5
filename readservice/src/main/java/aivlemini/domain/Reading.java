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

    }

    public static ReadingRepository repository() {
        ReadingRepository readingRepository = ReadserviceApplication.applicationContext.getBean(
            ReadingRepository.class
        );
        return readingRepository;
    }

    //<<< Clean Arch / Port Method
    public static void readFail(OutOfPoint outOfPoint) {

        repository().findById(outOfPoint.getReadingId()).ifPresent(reading -> {
            reading.setIsPurchase(reading.getIsPurchase());
            
            reading.setStatusMessage("실패: 포인트부족"); // 메시지 변경
            repository().save(reading);

            ReadFailed readFailed = new ReadFailed(reading);
            readFailed.publishAfterCommit();
        }); 
        

    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public static void readSuccess(PointDecreased pointDecreased) {

        repository().findById(pointDecreased.getReadingId()).ifPresent(reading -> {
            reading.setIsPurchase(reading.getIsPurchase());
            reading.setStatusMessage("성공");
            // 1번
            repository().save(reading);

            ReadSucceed readSucceed = new ReadSucceed(reading);
            readSucceed.publishAfterCommit();
        });
        

    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
