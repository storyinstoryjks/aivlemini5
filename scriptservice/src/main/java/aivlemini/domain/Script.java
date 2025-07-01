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

    private String Status;

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
        this.Status = "출간요청됨";
        this.id = requestPublishCommand.getId();
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
        this.Status = "임시저장됨";
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
        this.Status = "저장됨";
        this.content = saveScriptCommand.getContent();
        repository().save(this);

        ScriptSaved scriptSaved = new ScriptSaved(this);
        scriptSaved.publishAfterCommit();


    }

    //>>> Clean Arch / Port Method

    //<<< Clean Arch / Port Method
    public static void statusNotify(PublishPrepared publishPrepared) {
    repository().findById(publishPrepared.getManuscriptId()).ifPresent(script -> {
        if (Boolean.TRUE.equals(publishPrepared.getNotifyStatus())) {
            script.setStatus("출판 준비중");
        } else {
            script.setStatus("반려됨");
        }
        repository().save(script);
    });
}


}
//>>> DDD / Aggregate Root
