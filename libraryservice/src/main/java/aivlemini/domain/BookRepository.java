package aivlemini.domain;

import aivlemini.domain.*;
import java.util.Date;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

//<<< PoEAA / Repository
@RepositoryRestResource(collectionResourceRel = "books", path = "books")
public interface BookRepository extends PagingAndSortingRepository<Book, Long> {
    @Query(
        value = "select book " +
        "from Book book " +
        "where(:id is null or book.id = :id) and (:title is null or book.title like %:title%) and (:authorName is null or book.authorName like %:authorName%) and (:category is null or book.category like %:category%) and (:content is null or book.content like %:content%) and (:summaryContent is null or book.summaryContent like %:summaryContent%) and (:image is null or book.image like %:image%) and (:pdfPath is null or book.pdfPath like %:pdfPath%) and (:price is null or book.price = :price) and (book.isBestSeller = :isBestSeller) and (:subscriptionCount is null or book.subscriptionCount = :subscriptionCount)"
    )
    Book getAllLibrary(
        Long id,
        String title,
        String authorName,
        String category,
        String content,
        String summaryContent,
        String image,
        String pdfPath,
        Long price,
        Boolean isBestSeller,
        Long subscriptionCount
    );
}
