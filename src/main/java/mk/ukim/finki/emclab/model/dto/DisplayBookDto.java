package mk.ukim.finki.emclab.model.dto;

import mk.ukim.finki.emclab.model.domain.Author;
import mk.ukim.finki.emclab.model.domain.Book;
import mk.ukim.finki.emclab.model.enumeration.BookCategory;
import mk.ukim.finki.emclab.model.enumeration.BookState;

import java.time.LocalDate;
import java.util.List;

public record DisplayBookDto (
        Long id,
        String name,
        BookCategory category,
        Long authorId,
        BookState state,
        Integer availableCopies,
        LocalDate datePublished
) {
    public static DisplayBookDto from(Book book){
        return new DisplayBookDto(
                book.getId(),
                book.getName(),
                book.getCategory(),
                book.getAuthor().getId(),
                book.getState(),
                book.getAvailableCopies(),
                book.getDatePublished()
        );
    }

    public static List<DisplayBookDto> from (List<Book> books){
        return books
                .stream()
                .map(DisplayBookDto::from)
                .toList();
    }
}
