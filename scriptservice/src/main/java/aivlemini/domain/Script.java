package aivlemini.domain;

import aivlemini.ScriptserviceApplication;
import aivlemini.domain.ScriptSaved;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Script_table")
@Data
//<<< DDD / Aggregate Root
public class Script {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    private String content;

    private Long authorId;

    private String authorName;

    private String notifyStatus;

    @PostPersist
    public void onPostPersist() {
        ScriptSaved scriptSaved = new ScriptSaved(this);
        scriptSaved.publishAfterCommit();
    }

    public static ScriptRepository repository() {
        ScriptRepository scriptRepository = ScriptserviceApplication.applicationContext.getBean(
            ScriptRepository.class
        );
        return scriptRepository;
    }

    //<<< Clean Arch / Port Method
    public void requestPublish(RequestPublishCommand requestPublishCommand) {
        //implement business logic here:

        PublicationRequested publicationRequested = new PublicationRequested(
            this
        );
        publicationRequested.publishAfterCommit();
    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public void saveTemporaryScript(
        SaveTemporaryScriptCommand saveTemporaryScriptCommand
    ) {
        //implement business logic here:

        TemporaryScriptSaved temporaryScriptSaved = new TemporaryScriptSaved(
            this
        );
        temporaryScriptSaved.publishAfterCommit();
    }

    //>>> Clean Arch / Port Method

    //<<< Clean Arch / Port Method
    public static void statusNotify(PublishPrepared publishPrepared) {
        //implement business logic here:

        /** Example 1:  new item 
        Script script = new Script();
        repository().save(script);

        */

        /** Example 2:  finding and process
        
        // if publishPrepared.gptIdscriptId exists, use it
        
        // ObjectMapper mapper = new ObjectMapper();
        // Map<, Object> publishingMap = mapper.convertValue(publishPrepared.getGptId(), Map.class);
        // Map<Long, Object> publishingMap = mapper.convertValue(publishPrepared.getScriptId(), Map.class);

        repository().findById(publishPrepared.get???()).ifPresent(script->{
            
            script // do something
            repository().save(script);


         });
        */

    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
