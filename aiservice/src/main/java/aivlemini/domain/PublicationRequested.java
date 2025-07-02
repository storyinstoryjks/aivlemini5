package aivlemini.domain;

import aivlemini.domain.*;
import aivlemini.infra.AbstractEvent;
import java.util.*;

import javax.persistence.Lob;

import lombok.*;

@Data
@ToString
public class PublicationRequested extends AbstractEvent {

    private Long id;
    private String title;
    @Lob
    private String content;
    private Long authorId;
    private String authorName;
    private String Status; // 원고의 PublicationRequested랑 통일
}
