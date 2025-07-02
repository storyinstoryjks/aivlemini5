package aivlemini.domain;

import javax.persistence.Lob;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class BookSearchCondition {
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

}