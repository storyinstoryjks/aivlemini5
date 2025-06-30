package aivlemini.domain;

import aivlemini.domain.*;
import aivlemini.infra.AbstractEvent;
import java.time.LocalDate;
import java.util.*;
import lombok.*;

//<<< DDD / Domain Event
@Data
@ToString
public class ReadReceived extends AbstractEvent {

    private Long id;
    private String title;
    private String authorName;
    private String category;
    private String content;
    private String summaryContent;
    private String image;
    private String pdfPath;
    private Long price;
    private Boolean isBestSeller;
    private Long subscriptionCount;
    private Boolean isPurchase;

    public ReadReceived(Book aggregate) {
        super(aggregate);
    }

    public ReadReceived() {
        super();
    }
}
//>>> DDD / Domain Event
