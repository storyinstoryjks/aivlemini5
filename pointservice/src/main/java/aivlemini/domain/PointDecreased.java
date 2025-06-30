package aivlemini.domain;

import aivlemini.domain.*;
import aivlemini.infra.AbstractEvent;
import java.time.LocalDate;
import java.util.*;
import lombok.*;

//<<< DDD / Domain Event
@Data
@ToString
public class PointDecreased extends AbstractEvent {

    private Long id;
    private Long point;
    private Long readingId;
    private Long userId;

    public PointDecreased(Point aggregate) {
        super(aggregate);
        this.id = aggregate.getId();
        this.point = aggregate.getPoint();
        this.userId = aggregate.getUserId();
        // readingId는 event로 발행하면서 setReadingId
    }

    public PointDecreased() {
        super();
    }
}
//>>> DDD / Domain Event
