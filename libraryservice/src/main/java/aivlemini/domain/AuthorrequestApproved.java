package aivlemini.domain;

import aivlemini.infra.AbstractEvent;
import java.time.LocalDate;
import java.util.*;
import lombok.Data;

@Data
public class AuthorrequestApproved extends AbstractEvent {

    private Long id;
    private String authorName;
    private String email;
    private String phone;
    private String introduction;
    private String portfolioUrl;
    private Boolean isApproved;
}
