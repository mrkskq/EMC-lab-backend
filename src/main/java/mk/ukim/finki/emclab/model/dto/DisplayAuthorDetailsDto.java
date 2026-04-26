package mk.ukim.finki.emclab.model.dto;

import mk.ukim.finki.emclab.model.domain.Author;
import mk.ukim.finki.emclab.model.domain.Book;
import mk.ukim.finki.emclab.model.domain.Country;
import mk.ukim.finki.emclab.model.enumeration.BookCategory;
import mk.ukim.finki.emclab.model.enumeration.BookState;

public record DisplayAuthorDetailsDto(
        Long id,
        String name,
        String surname,
        DisplayCountryDto country
) {
    public static DisplayAuthorDetailsDto from(Author author) {
        return new DisplayAuthorDetailsDto(
                author.getId(),
                author.getName(),
                author.getSurname(),
                DisplayCountryDto.from(author.getCountry())
        );
    }
}