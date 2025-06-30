package aivlemini.domain;

import aivlemini.domain.*;
import java.util.Date;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.repository.query.Param;

//<<< PoEAA / Repository
@RepositoryRestResource(collectionResourceRel = "points", path = "points")
public interface PointRepository
        extends PagingAndSortingRepository<Point, Long> {
    @Query("SELECT p FROM Point p " +
            "WHERE (:id IS NULL OR p.id = :id) " +
            "AND (:point IS NULL OR p.point = :point) " +
            "AND (:userId IS NULL OR p.userId = :userId)")
    Point getAllPoint(
            @Param("id") Long id,
            @Param("point") Long point,
            @Param("userId") Long userId);

    Point findByUserId(Long userId); // Spring Data JPA 메서드 추가
}
