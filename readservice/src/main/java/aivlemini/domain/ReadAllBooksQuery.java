package aivlemini.domain;

import java.util.Date;
import lombok.Data;

@Data
public class ReadAllBooksQuery {

    private Long id;
    private Boolean isPurchase;
    private String statusMessage;
    private Long userId;
    private Long bookId;
}
