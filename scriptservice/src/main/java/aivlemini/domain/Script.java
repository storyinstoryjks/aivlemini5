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

    @Lob
    private String content;

    private Long authorId;

    private String authorName;

    private String Status;

    // @PostPersist
    // public void onPostPersist() {
    //     //System.out.println("=== [Manuscript] onPostPersist called");
    //     ScriptSaved scriptSaved = new ScriptSaved(this);
    //     scriptSaved.publishAfterCommit();
    // }

    public static ScriptRepository repository() {
        ScriptRepository scriptRepository = ScriptserviceApplication.applicationContext.getBean(
            ScriptRepository.class
        );
        return scriptRepository;
    }


    //<<< Clean Arch / Port 
    public void saveScript(SaveScriptCommand saveScriptCommand) {
        this.setAuthorId(saveScriptCommand.getAuthorId());
        this.setAuthorName(saveScriptCommand.getAuthorName());
        this.setContent(saveScriptCommand.getContent());
        this.setTitle(saveScriptCommand.getTitle());
        this.setStatus("최초저장됨");
        ScriptSaved scriptSaved = new ScriptSaved(this);
        scriptSaved.publishAfterCommit();
    }
    //<<< Clean Arch / Port Method

    public void saveTemporaryScript(
        SaveTemporaryScriptCommand saveTemporaryScriptCommand
    ) {
        // 보통 원고의 제목과 내용만 바뀌기 때문에 2가지만 적용
        this.Status = "임시저장됨"; // this.setStatus(saveTemporaryScriptCommand.getStatus());
        this.content = saveTemporaryScriptCommand.getContent();
        this.title = saveTemporaryScriptCommand.getTitle();
        // repository().save(this); // Controller에서 진행

        TemporaryScriptSaved temporaryScriptSaved = new TemporaryScriptSaved(
            this
        );
        temporaryScriptSaved.publishAfterCommit();
    }


    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public void requestPublish(RequestPublishCommand requestPublishCommand) {
        this.Status = "출간요청됨"; // this.setStatus(requestPublishCommand.getStatus());
        PublicationRequested publicationRequested = new PublicationRequested(
            this
        );
        publicationRequested.publishAfterCommit();
    }

    //>>> Clean Arch / Port Method
    
    public static void statusNotify(PublishPrepared publishPrepared) {
    repository().findById(publishPrepared.getManuscriptId()).ifPresent(script -> {
        if (Boolean.TRUE.equals(publishPrepared.getNotifyStatus())) {
            script.setStatus("출간됨"); // 출판준비중 -> 출간됨
        } else {
            script.setStatus("반려됨");
        }
        repository().save(script);
    });
}


}
//>>> DDD / Aggregate Root
