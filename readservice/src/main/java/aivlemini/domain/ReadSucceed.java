package aivlemini.domain;

import aivlemini.domain.*;
import aivlemini.infra.AbstractEvent;
import java.time.LocalDate;
import java.util.*;
import lombok.*;

//<<< DDD / Domain Event
@Data
@ToString
public class ReadSucceed extends AbstractEvent {

    private Long id;
    private Boolean isPurchase;
    private String statusMessage;
    private Long userId;
    private Long bookId;

    public ReadSucceed(Reading aggregate) {
        super(aggregate);
    }

    public ReadSucceed() {
        super();
    }
}
//>>> DDD / Domain Event
