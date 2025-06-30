package aivlemini.domain;

import java.util.Date;
import lombok.Data;

@Data
public class GetAllLibraryQuery {

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
