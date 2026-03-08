package mk.ukim.finki.emclab.model.dto;

import mk.ukim.finki.emclab.model.domain.Author;
import mk.ukim.finki.emclab.model.domain.Country;

import java.util.List;

public record DisplayAuthorDto (
    Long id,
    String name,
    String surname,
    Long countryId
) {
    public static DisplayAuthorDto from (Author author){
        return new DisplayAuthorDto(
          author.getId(),
          author.getName(),
          author.getSurname(),
          author.getCountry().getId()
        );
    }

    public static List<DisplayAuthorDto> from (List<Author> authors){
        return authors
                .stream()
                .map(DisplayAuthorDto::from)
                .toList();
    }
}
