package aivlemini.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BookView {

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
}