package mk.ukim.finki.emclab.service.application.impl;

import mk.ukim.finki.emclab.model.domain.Country;
import mk.ukim.finki.emclab.model.dto.CreateCountryDto;
import mk.ukim.finki.emclab.model.dto.DisplayCountryDetailsDto;
import mk.ukim.finki.emclab.model.dto.DisplayCountryDto;
import mk.ukim.finki.emclab.model.exception.CountryNotFoundException;
import mk.ukim.finki.emclab.repository.CountryRepository;
import mk.ukim.finki.emclab.service.application.CountryApplicationService;
import mk.ukim.finki.emclab.service.domain.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryApplicationServiceImpl implements CountryApplicationService {

    private final CountryService countryService;

    public CountryApplicationServiceImpl(CountryService countryService) {
        this.countryService = countryService;
    }

    @Override
    public Optional<DisplayCountryDto> findById(Long id) {
        return countryService
                .findById(id)
                .map(DisplayCountryDto::from);
    }

    @Override
    public List<DisplayCountryDto> findAll() {
        return DisplayCountryDto.from(countryService.findAll());
    }

    @Override
    public DisplayCountryDto create(CreateCountryDto createCountryDto) {
        return DisplayCountryDto.from(countryService.create(createCountryDto.toCountry(null)));
    }

    @Override
    public Optional<DisplayCountryDto> update(Long id, CreateCountryDto createCountryDto) {
       return countryService
                .update(id, createCountryDto.toCountry(null))
                .map(DisplayCountryDto::from);
    }

    @Override
    public Optional<DisplayCountryDto> deleteById(Long id) {
        return countryService
                .deleteById(id)
                .map(DisplayCountryDto::from);
    }

    @Override
    public Optional<DisplayCountryDetailsDto> findWithDetailsById(Long id) {
        return countryService
                .findById(id)
                .map(DisplayCountryDetailsDto::from);
    }
}
