package aivlemini.domain;

import aivlemini.domain.*;
import aivlemini.infra.AbstractEvent;
import java.time.LocalDate;
import java.util.*;
import lombok.*;

//<<< DDD / Domain Event
@Data
@ToString
public class RegisterPointGained extends AbstractEvent {

    private Long id;
    private Long point;
    private Long userId;

    public RegisterPointGained(Point aggregate) {
        super(aggregate);
    }

    public RegisterPointGained() {
        super();
    }
}
//>>> DDD / Domain Event
