package mk.ukim.finki.emclab.model.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import mk.ukim.finki.emclab.model.domain.Author;
import mk.ukim.finki.emclab.model.domain.Book;
import mk.ukim.finki.emclab.model.enumeration.BookCategory;
import mk.ukim.finki.emclab.model.enumeration.BookState;

public record CreateBookDto (
        @NotBlank(message = "Name is required")
        String name,

        @NotNull(message = "Category is required")
        BookCategory category,

        @NotNull(message = "Author is required")
        Long authorId,

        @NotNull(message = "State is required")
        BookState state,

        @NotNull(message = "Available copies is required")
        @Min(value = 0, message = "Available copies can not be negative")
        Integer availableCopies
) {
    public Book toBook(Author author){
        return new Book(name, category, author, state, availableCopies);
    }
}
