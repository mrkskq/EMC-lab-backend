package mk.ukim.finki.emclab.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import mk.ukim.finki.emclab.model.domain.Author;
import mk.ukim.finki.emclab.model.domain.Country;

public record CreateAuthorDto (
        @NotBlank(message = "Name is required")
        String name,

        @NotBlank(message = "Surname is required")
        String surname,

        @NotNull(message = "Country is required")
        Long countryId
) {
    public Author toAuthor (Country country){
        return new Author(name, surname, country);
    }
}
