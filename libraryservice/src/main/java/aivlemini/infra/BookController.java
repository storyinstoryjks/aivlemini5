package aivlemini.infra;

import aivlemini.domain.*;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;


//<<< Clean Arch / Inbound Adaptor

@RestController
@RequestMapping(value="/books")
@Transactional
public class BookController {
    private final BookRepository bookRepository;
    private final BookQueryService bookQueryService;

    public BookController(BookRepository bookRepository,BookQueryService bookQueryService){
        this.bookRepository = bookRepository;
        this.bookQueryService=bookQueryService;
    }

    @PostMapping
    public Book createBook(@RequestBody Book book) {
        return bookRepository.save(book);
    }

    @GetMapping
    public List<BookView> getBooks() {
        return bookQueryService.getAllBooks();
    }
      // 조회 수 증가 API 추가
    @PostMapping("/{id}/subscribe")
    public String subscribeBook(@PathVariable("id") Long id) {
        ReadSucceed read = new ReadSucceed();
        read.setBookId(id);

        Book.grantBestseller(read);

        return "조회수 +1 증가, 조회수 5이상은 베스트셀러 등록 ";
    }
    // @Autowired
    // BookRepository bookRepository;
}
//>>> Clean Arch / Inbound Adaptor
