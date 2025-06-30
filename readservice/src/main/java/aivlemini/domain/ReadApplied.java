package aivlemini.domain;

import aivlemini.domain.*;
import aivlemini.infra.AbstractEvent;
import java.time.LocalDate;
import java.util.*;
import lombok.*;

//<<< DDD / Domain Event
@Data
@ToString
public class ReadApplied extends AbstractEvent {

    private Long id;
    private Boolean isPurchase;
    private String statusMessage;
    private Long userId;
    private Long bookId;

    public ReadApplied(Reading aggregate) {
        super(aggregate);
    }

    public ReadApplied() {
        super();
    }
}
//>>> DDD / Domain Event
