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
public class ReadReceived extends AbstractEvent {

    private Long id;
    private String title;
    private String authorName;
    private String category;
    @Lob
    private String content;
    @Lob
    private String summaryContent;
    @Lob
    private String image;
    @Lob
    private String pdfPath;
    private Long price;
    private Boolean isBestSeller;
    private Long subscriptionCount;
    private Boolean isPurchase;
    private Long readingId;
    private Long userId; // 추가

    public ReadReceived(Book aggregate) {
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

    public ReadReceived() {
        super();
    }
}
//>>> DDD / Domain Event
