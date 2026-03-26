package mk.ukim.finki.emclab.repository;

import mk.ukim.finki.emclab.model.domain.Book;
import mk.ukim.finki.emclab.model.enumeration.BookCategory;
import mk.ukim.finki.emclab.model.enumeration.BookState;
import mk.ukim.finki.emclab.model.projection.BookDetailedProjection;
import mk.ukim.finki.emclab.model.projection.BookShortProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    // lab2 - 1. za pagination
    Page<Book> findAll(Pageable pageable);

    Page<Book> findByCategoryAndStateAndAuthorNameContainingAndAvailableCopiesGreaterThan(
            BookCategory category,
            BookState state,
            String authorName,
            Integer availableCopies,
            Pageable pageable
    );

    // lab2 - 2. za projections
    List<BookShortProjection> findAllShortBy();
    List<BookDetailedProjection> findAllDetailedBy();

    // lab2 - 3. za entity graph
    @EntityGraph(value = "book-entity-graph", type = EntityGraph.EntityGraphType.FETCH)
    List<Book> findAll();

    List<Book> findTop10ByDatePublishedBefore(LocalDate date);
}
