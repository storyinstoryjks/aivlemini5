package aivlemini.domain;

import aivlemini.domain.*;
import aivlemini.infra.AbstractEvent;
import java.util.*;
import lombok.*;

@Data
@ToString
public class ReadApplied extends AbstractEvent {

    private Long id;
    private Boolean isPurchase;
    private String statusMessage;
    private Long userId;
    private Long bookId;
}
