package aivlemini.infra;

import aivlemini.domain.*;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

        return "조회수 +1 증가";
    }
    // 열람 신청 중 이벤트 API 테스트용  (API 보낼때 ReadApplied에 있는 속성들을 보내는게 맞나?)
    @PostMapping("/{id}/readApplying")
    public String applyRead(@PathVariable Long id, @RequestBody ReadApplied readApplied) {
        if (!id.equals(readApplied.getBookId())) {
            return "bookId 맞지않음.";
        }

        Book.readReceive(readApplied); // 이벤트 처리 호출
        return "처리 완료";
    }

    // frontend의 도서 조회(검색에서 사용할 컨트롤러 함수
    @PostMapping("/search/getAllLibrary")
    public List<Book> searchBooks(@RequestBody BookSearchCondition filter) {
        return bookRepository.findAll(BookSpecification.withFilters(
            filter.getId(),
            filter.getTitle(),
            filter.getAuthorName(),
            filter.getCategory(),
            filter.getContent(),
            filter.getSummaryContent(),
            filter.getImage(),
            filter.getPdfPath(),
            filter.getPrice(),
            filter.getIsBestSeller(),
            filter.getSubscriptionCount()
        ));
    }

    // frontend의 도서 조회에서 사용할 컨트롤러 함수
    @GetMapping("/getAllLibraries")
    public List<Book> allBooks() {
        return (List<Book>)bookRepository.findAll();
    }

    // @Autowired
    // BookRepository bookRepository;
}
//>>> Clean Arch / Inbound Adaptor
