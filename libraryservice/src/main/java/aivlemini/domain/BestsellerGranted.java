package aivlemini.domain;

import aivlemini.domain.*;
import aivlemini.infra.AbstractEvent;
import java.time.LocalDate;
import java.util.*;
import lombok.*;

//<<< DDD / Domain Event
@Data
@ToString
public class BestsellerGranted extends AbstractEvent {

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

    public BestsellerGranted(Book aggregate) {
        super(aggregate);
        this.id=aggregate.getId();
        this.title=aggregate.getTitle();
        this.authorName=aggregate.getAuthorName();
        this.category=aggregate.getCategory();
        this.content=aggregate.getContent();
        this.summaryContent=aggregate.getSummaryContent();
        this.image=aggregate.getImage();
        this.pdfPath=aggregate.getPdfPath();
        this.price=aggregate.getPrice();
        this.isBestSeller=aggregate.getIsBestSeller();
        this.subscriptionCount=aggregate.getSubscriptionCount();
    }

    public BestsellerGranted() {
        super();
    }
}
//>>> DDD / Domain Event
