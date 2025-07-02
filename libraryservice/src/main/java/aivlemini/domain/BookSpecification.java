package aivlemini.domain;

import org.springframework.data.jpa.domain.Specification;
import aivlemini.domain.Book;

public class BookSpecification {

    public static Specification<Book> withFilters(
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
    ) {
        return (root, query, cb) -> {
            var predicates = cb.conjunction();

            if (id != null) {
                predicates = cb.and(predicates, cb.equal(root.get("id"), id));
            }
            if (title != null) {
                predicates = cb.and(predicates, cb.like(root.get("title"), "%" + title + "%"));
            }
            if (authorName != null) {
                predicates = cb.and(predicates, cb.like(root.get("authorName"), "%" + authorName + "%"));
            }
            if (category != null) {
                predicates = cb.and(predicates, cb.like(root.get("category"), "%" + category + "%"));
            }
            if (content != null) {
                predicates = cb.and(predicates, cb.like(root.get("content"), "%" + content + "%"));
            }
            if (summaryContent != null) {
                predicates = cb.and(predicates, cb.like(root.get("summaryContent"), "%" + summaryContent + "%"));
            }
            if (image != null) {
                predicates = cb.and(predicates, cb.like(root.get("image"), "%" + image + "%"));
            }
            if (pdfPath != null) {
                predicates = cb.and(predicates, cb.like(root.get("pdfPath"), "%" + pdfPath + "%"));
            }
            if (price != null) {
                predicates = cb.and(predicates, cb.equal(root.get("price"), price));
            }
            if (isBestSeller != null) {
                predicates = cb.and(predicates, cb.equal(root.get("isBestSeller"), isBestSeller));
            }
            if (subscriptionCount != null) {
                predicates = cb.and(predicates, cb.equal(root.get("subscriptionCount"), subscriptionCount));
            }

            return predicates;
        };
    }
}
