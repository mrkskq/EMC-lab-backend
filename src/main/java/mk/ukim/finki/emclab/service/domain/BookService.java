package mk.ukim.finki.emclab.service.domain;

import mk.ukim.finki.emclab.model.domain.Book;
import mk.ukim.finki.emclab.model.dto.DisplayBookDto;
import mk.ukim.finki.emclab.model.dto.DisplayBookListDto;
import mk.ukim.finki.emclab.model.enumeration.BookCategory;
import mk.ukim.finki.emclab.model.enumeration.BookState;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface BookService {
    Optional<Book> findById(Long id);

    List<Book> findAll();

    Book create(Book book);

    Optional<Book> update(Long id, Book book);

    Optional<Book> deleteById(Long id);

    Optional<Book> rent(Long id);

    // lab2 - 1. za pagination
    Page<DisplayBookListDto> findAll(int page, int size, String sortBy);

    Page<DisplayBookListDto> listBooks(
            BookCategory category,
            BookState state,
            String authorName,
            Boolean available,
            int page,
            int size,
            String sortBy
    );

    List<Book> findTop10NewestBooks();
}
