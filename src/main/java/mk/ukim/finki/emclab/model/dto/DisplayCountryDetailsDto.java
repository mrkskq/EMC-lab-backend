package mk.ukim.finki.emclab.model.dto;

import mk.ukim.finki.emclab.model.domain.Author;
import mk.ukim.finki.emclab.model.domain.Country;

public record DisplayCountryDetailsDto(
        Long id,
        String name,
        String continent
) {
    public static DisplayCountryDetailsDto from(Country country) {
        return new DisplayCountryDetailsDto(
                country.getId(),
                country.getName(),
                country.getContinent()
        );
    }
}