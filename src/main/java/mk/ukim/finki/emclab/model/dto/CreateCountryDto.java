package mk.ukim.finki.emclab.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import mk.ukim.finki.emclab.model.domain.Country;

public record CreateCountryDto (
        @NotBlank(message = "Name is required")
        String name,

        @NotBlank(message = "Continent is required")
        String continent
) {
    public Country toCountry (Country country){
        return new Country(name, continent);
    }
}
