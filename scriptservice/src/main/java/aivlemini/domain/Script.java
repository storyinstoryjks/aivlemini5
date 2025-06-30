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

    private String notifyStatus = "저장됨";

    @PostPersist
    public void onPostPersist() {
        
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
        this.notifyStatus = "임시저장됨";
        this.content = saveTemporaryScriptCommand.getContent();
        repository().save(this);

        TemporaryScriptSaved temporaryScriptSaved = new TemporaryScriptSaved(
            this
        );
        temporaryScriptSaved.publishAfterCommit();
    }

    public void saveScript(
        SaveScriptCommand saveScriptCommand
    ) {
        this.notifyStatus = "저장됨";
        this.content = saveScriptCommand.getContent();
        repository().save(this);

        ScriptSaved scriptSaved = new ScriptSaved(this);
        scriptSaved.publishAfterCommit();


    }

    //>>> Clean Arch / Port Method

    //<<< Clean Arch / Port Method
    public static void statusNotify(PublishPrepared publishPrepared) {

        repository().findById(publishPrepared.getId()).ifPresent(script->{
            script.setnotifyStatus(publishPrepared.getnotifyStatus());
            script.setTitle(publishPrepared.getTitle());
            script.setContents(publishPrepared.getSummaryContent());
            repository().save(script);


         });
    }


}
//>>> DDD / Aggregate Root
