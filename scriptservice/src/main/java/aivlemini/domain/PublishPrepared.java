package aivlemini.domain;

import aivlemini.domain.*;
import aivlemini.infra.AbstractEvent;
import java.util.*;
import lombok.*;

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
    private boolean notifyStatus;
    private Long manuscriptId;
}
