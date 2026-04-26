package mk.ukim.finki.emclab.service.application;

import mk.ukim.finki.emclab.model.dto.CreateCountryDto;
import mk.ukim.finki.emclab.model.dto.DisplayCountryDetailsDto;
import mk.ukim.finki.emclab.model.dto.DisplayCountryDto;

import java.util.List;
import java.util.Optional;

public interface CountryApplicationService {
    Optional<DisplayCountryDto> findById(Long id);

    List<DisplayCountryDto> findAll();

    DisplayCountryDto create(CreateCountryDto createCountryDto);

    Optional<DisplayCountryDto> update(Long id, CreateCountryDto createCountryDto);

    Optional<DisplayCountryDto> deleteById(Long id);

    Optional<DisplayCountryDetailsDto> findWithDetailsById(Long id);
}
