package aivlemini.domain;

import aivlemini.domain.*;
import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

//<<< PoEAA / Repository
@RepositoryRestResource(collectionResourceRel = "books", path = "books")
public interface BookRepository extends PagingAndSortingRepository<Book, Long>, JpaSpecificationExecutor<Book> {
    @Query(
        value = "select book " +
        "from Book book " +
        "where(:id is null or book.id = :id) and (:title is null or book.title like concat('%', :title, '%')) and (:authorName is null or book.authorName like concat('%', :authorName, '%')) and (:category is null or book.category like concat('%', :category, '%')) and (:content is null or book.content like concat('%', :content, '%')) and (:summaryContent is null or book.summaryContent like concat('%', :summaryContent, '%')) and (:image is null or book.image like concat('%', :image, '%')) and (:pdfPath is null or book.pdfPath like concat('%', :pdfPath, '%')) and (:price is null or book.price = :price) and (:isBestSeller is null or book.isBestSeller = :isBestSeller) and (:subscriptionCount is null or book.subscriptionCount = :subscriptionCount)"
    )
    List<Book> findBooks(
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
