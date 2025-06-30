package aivlemini.domain;

import aivlemini.domain.*;
import aivlemini.infra.AbstractEvent;
import java.time.LocalDate;
import java.util.*;
import lombok.*;

//<<< DDD / Domain Event
@Data
@ToString
public class PublishPrepared extends AbstractEvent {

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

    public PublishPrepared(Publishing aggregate) {
        super(aggregate);
    }

    public PublishPrepared() {
        super();
    }
}
//>>> DDD / Domain Event
