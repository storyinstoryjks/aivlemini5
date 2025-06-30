package aivlemini.domain;

import aivlemini.domain.*;
import aivlemini.infra.AbstractEvent;
import java.util.*;
import lombok.*;

@Data
@ToString
public class UserRegistered extends AbstractEvent {

    private Long id;
    private String userName;
    private Boolean isPurchase;
    private Date planStartDate;
    private Date planEndDate;
    private String loginId;
    private String loginPw;
}
