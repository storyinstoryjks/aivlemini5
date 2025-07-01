package aivlemini.domain;

import aivlemini.domain.*;
import aivlemini.infra.AbstractEvent;
import java.util.*;
import lombok.*;

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
    private Long userId; // userId 추가
    private Long readingId; // readingId 추가
}
