package aivlemini.domain;

import aivlemini.domain.*;
import java.util.Date;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

//<<< PoEAA / Repository
@RepositoryRestResource(collectionResourceRel = "readings", path = "readings")
public interface ReadingRepository
    extends PagingAndSortingRepository<Reading, Long> {
    @Query(
        value = "select reading " +
        "from Reading reading " +
        "where(:id is null or reading.id = :id) and (reading.isPurchase = :isPurchase) and (:statusMessage is null or reading.statusMessage like %:statusMessage%) and (:userId is null or reading.userId = :userId) and (:bookId is null or reading.bookId = :bookId)"
    )
    Reading readAllBooks(
        Long id,
        Boolean isPurchase,
        String statusMessage,
        Long userId,
        Long bookId
    );
}
