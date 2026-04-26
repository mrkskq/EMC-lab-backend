package mk.ukim.finki.emclab.model.dto;

import mk.ukim.finki.emclab.model.domain.Book;
import mk.ukim.finki.emclab.model.enumeration.BookCategory;
import mk.ukim.finki.emclab.model.enumeration.BookState;

import java.util.List;

public record DisplayBookDetailsDto(
        Long id,
        String name,
        BookCategory category,
        DisplayAuthorDto author,
        BookState state,
        Integer availableCopies
) {
    public static DisplayBookDetailsDto from(Book book) {
        return new DisplayBookDetailsDto(
                book.getId(),
                book.getName(),
                book.getCategory(),
                DisplayAuthorDto.from(book.getAuthor()),
                book.getState(),
                book.getAvailableCopies()
        );
    }
}