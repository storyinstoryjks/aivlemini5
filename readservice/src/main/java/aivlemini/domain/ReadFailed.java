package aivlemini.domain;

import aivlemini.domain.*;
import aivlemini.infra.AbstractEvent;
import java.time.LocalDate;
import java.util.*;
import lombok.*;

//<<< DDD / Domain Event
@Data
@ToString
public class ReadFailed extends AbstractEvent {

    private Long id;
    private Boolean isPurchase;
    private String statusMessage;
    private Long userId;
    private Long bookId;

    public ReadFailed(Reading aggregate) {
        super(aggregate);
    }

    public ReadFailed() {
        super();
    }
}
//>>> DDD / Domain Event
