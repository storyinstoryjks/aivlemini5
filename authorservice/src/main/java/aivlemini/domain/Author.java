package aivlemini.domain;

import aivlemini.AuthorserviceApplication;
import aivlemini.domain.AuthorRejected;
import aivlemini.domain.AuthorRequested;
import aivlemini.domain.AuthorrequestApproved;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Author_table")
@Data
//<<< DDD / Aggregate Root
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String authorName;

    private String email;

    private String phone;

    private String introduction;

    private String portfolioUrl;

    private Boolean isApproved;

    @PostPersist
    public void onPostPersist() {
        AuthorRequested authorRequested = new AuthorRequested(this);
        authorRequested.publishAfterCommit();
    }

    public static AuthorRepository repository() {
        AuthorRepository authorRepository = AuthorserviceApplication.applicationContext.getBean(
            AuthorRepository.class
        );
        return authorRepository;
    }

    //<<< Clean Arch / Port Method
    public void approveAuthor(ApproveAuthorCommand approveAuthorCommand) {
        this.setIsApproved(approveAuthorCommand.getIsApproved()); // isApproved를 true로 변경

        AuthorrequestApproved authorrequestApproved = new AuthorrequestApproved(this);
        authorrequestApproved.publishAfterCommit();
    }

    //>>> Clean Arch / Port Method

    //<<< Clean Arch / Port Method
    public void disapproveAuthor(DisapproveAuthorCommand disapproveAuthorCommand) {
        this.setIsApproved(disapproveAuthorCommand.getIsApproved()); // isApproved를 false로 변경

        AuthorRejected authorRejected = new AuthorRejected(this);
        authorRejected.publishAfterCommit();
    }
}
//>>> DDD / Aggregate Root
