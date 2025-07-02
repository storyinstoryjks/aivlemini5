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
public class PublishPrepared extends AbstractEvent {

    private Long id;
    private String title;
    private Long authorId;
    private String authorName;
    private String category;
    @Lob
    private String content;
    @Lob
    private String summaryContent;
    @Lob
    private String coverImagePath;
    @Lob
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
