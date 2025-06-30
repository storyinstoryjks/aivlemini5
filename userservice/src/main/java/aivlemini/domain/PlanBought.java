package aivlemini.domain;

import aivlemini.domain.*;
import aivlemini.infra.AbstractEvent;
import java.time.LocalDate;
import java.util.*;
import lombok.*;

//<<< DDD / Domain Event
@Data
@ToString
public class PlanBought extends AbstractEvent {

    private Long id;
    private String userName;
    private Boolean isPurchase;
    private Date planStartDate;
    private Date planEndDate;
    private String loginId;
    private String loginPw;

    public PlanBought(UserInfo aggregate) {
        super(aggregate);
    }

    public PlanBought() {
        super();
    }
}
//>>> DDD / Domain Event
