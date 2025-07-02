package aivlemini.domain;

import java.time.LocalDate;
import java.util.*;

import javax.persistence.Lob;

import lombok.Data;

@Data
public class SaveScriptCommand {

    private String title;
    @Lob
    private String content;
    private Long authorId;
    private String authorName;
    private String status;
}
