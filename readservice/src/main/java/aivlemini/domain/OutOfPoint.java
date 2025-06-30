package aivlemini.domain;

import aivlemini.domain.*;
import aivlemini.infra.AbstractEvent;
import java.util.*;
import lombok.*;

@Data
@ToString
public class OutOfPoint extends AbstractEvent {

    private Long id;
    private Long point;
    private Long readingId;
    private Long userId;
}
