package aivlemini.domain;

import aivlemini.AiserviceApplication;
import aivlemini.domain.PublishPrepared;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Publishing_table")
@Data
//<<< DDD / Aggregate Root
public class Publishing {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    private Long authorId;

    private String authorName;

    private String category;

    private String content;

    private String summaryContent;

    private String coverImagePath;

    private String pdfPath;

    private Long price;

    private Boolean notifyStatus;

    private Long manuscriptId;

    public static PublishingRepository repository() {
        PublishingRepository publishingRepository = AiserviceApplication.applicationContext.getBean(
            PublishingRepository.class
        );
        return publishingRepository;
    }

    //<<< Clean Arch / Port Method
    public static void publish(PublicationRequested publicationRequested) {
        //implement business logic here:

        /** Example 1:  new item 
        Publishing publishing = new Publishing();
        repository().save(publishing);

        PublishPrepared publishPrepared = new PublishPrepared(publishing);
        publishPrepared.publishAfterCommit();
        */

        /** Example 2:  finding and process
        
        // if publicationRequested.authorId exists, use it
        
        // ObjectMapper mapper = new ObjectMapper();
        // Map<Long, Object> scriptMap = mapper.convertValue(publicationRequested.getAuthorId(), Map.class);

        repository().findById(publicationRequested.get???()).ifPresent(publishing->{
            
            publishing // do something
            repository().save(publishing);

            PublishPrepared publishPrepared = new PublishPrepared(publishing);
            publishPrepared.publishAfterCommit();

         });
        */

    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
