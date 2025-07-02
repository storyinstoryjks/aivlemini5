package aivlemini.domain;

import aivlemini.domain.*;
import aivlemini.infra.AbstractEvent;
import java.time.LocalDate;
import java.util.*;

import javax.persistence.Lob;

import lombok.*;

//<<< DDD / Domain Event
@Data
@ToString
public class ScriptSaved extends AbstractEvent {

    private Long id;
    private String title;
    @Lob
    private String content;
    private Long authorId;
    private String authorName;
    private String Status;

    public ScriptSaved(Script aggregate) {
        super(aggregate);
    }

    public ScriptSaved() {
        super();
    }
}
//>>> DDD / Domain Event
