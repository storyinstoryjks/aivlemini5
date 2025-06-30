package aivlemini.domain;

import aivlemini.domain.*;
import aivlemini.infra.AbstractEvent;
import java.time.LocalDate;
import java.util.*;
import lombok.*;

//<<< DDD / Domain Event
@Data
@ToString
public class AuthorRequested extends AbstractEvent {

    private Long id;
    private String authorName;
    private String email;
    private String phone;
    private String introduction;
    private String portfolioUrl;
    private Boolean isApproved;

    public AuthorRequested(Author aggregate) {
        super(aggregate);
    }

    public AuthorRequested() {
        super();
    }
}
//>>> DDD / Domain Event
