package aivlemini.domain;

import aivlemini.LibraryserviceApplication;
import aivlemini.domain.BestsellerGranted;
import aivlemini.domain.BookRegistered;
import aivlemini.domain.ReadReceived;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Book_table")
@Data
//<<< DDD / Aggregate Root
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    private String authorName;

    private String category;

    private String content;

    private String summaryContent;

    private String image;

    private String pdfPath;

    private Long price;

    private Boolean isBestSeller;

    private Long subscriptionCount;

    public static BookRepository repository() {
        BookRepository bookRepository = LibraryserviceApplication.applicationContext.getBean(
            BookRepository.class
        );
        return bookRepository;
    }

    //<<< Clean Arch / Port Method
    public static void registerBook(PublishPrepared publishPrepared) {

        Book book = new Book();
        book.setTitle(publishPrepared.getTitle());
        book.setCategory(publishPrepared.getCategory());
        book.setIsBestSeller(false);
        book.setAuthorName(publishPrepared.getAuthorName());
        book.setImage(publishPrepared.getCoverImagePath());
        book.setSubscriptionCount(0L);
        book.setSummaryContent(publishPrepared.getSummaryContent());
        book.setPdfPath(publishPrepared.getPdfPath());
        book.setContent(publishPrepared.getContent());
        book.setPrice(publishPrepared.getPrice());

        repository().save(book);
        BookRegistered bookRegistered = new BookRegistered(book);
        bookRegistered.publishAfterCommit();


    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public static void grantBestseller(ReadSucceed readSucceed) {
        Long bookId = readSucceed.getBookId();  

        repository().findById(bookId).ifPresent(book -> {

        if (book.getSubscriptionCount() == null) {
            book.setSubscriptionCount(0L);
        }

        book.setSubscriptionCount(book.getSubscriptionCount() + 1L);

        if (!Boolean.TRUE.equals(book.getIsBestSeller()) && book.getSubscriptionCount() >= 5) {
            book.setIsBestSeller(true);

            BestsellerGranted event = new BestsellerGranted(book);
            event.publishAfterCommit();
        }

        repository().save(book);
        });
        
    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public static void readReceive(ReadApplied readApplied) {

        repository().findById(readApplied.getBookId()).ifPresent(book->{
            
            // book // do something
            // repository().save(book);

            ReadReceived readReceived = new ReadReceived(book);
            // readReceived.setPrice(book.getPrice()); 
            readReceived.setIsPurchase(readApplied.getIsPurchase());
            readReceived.setReadingId(readApplied.getId());
            readReceived.publishAfterCommit();
         });
      

    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
