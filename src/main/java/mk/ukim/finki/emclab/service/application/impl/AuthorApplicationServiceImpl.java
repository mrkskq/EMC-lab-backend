package mk.ukim.finki.emclab.service.application.impl;

import mk.ukim.finki.emclab.model.domain.Country;
import mk.ukim.finki.emclab.model.dto.CreateAuthorDto;
import mk.ukim.finki.emclab.model.dto.DisplayAuthorDetailsDto;
import mk.ukim.finki.emclab.model.dto.DisplayAuthorDto;
import mk.ukim.finki.emclab.model.exception.CountryNotFoundException;
import mk.ukim.finki.emclab.repository.CountryRepository;
import mk.ukim.finki.emclab.service.application.AuthorApplicationService;
import mk.ukim.finki.emclab.service.domain.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorApplicationServiceImpl implements AuthorApplicationService {

    private final AuthorService authorService;
    private final CountryRepository countryRepository;

    public AuthorApplicationServiceImpl(AuthorService authorService, CountryRepository countryRepository) {
        this.authorService = authorService;
        this.countryRepository = countryRepository;
    }

    @Override
    public Optional<DisplayAuthorDto> findById(Long id) {
        return authorService
                .findById(id)
                .map(DisplayAuthorDto::from);
    }

    @Override
    public List<DisplayAuthorDto> findAll() {
        return DisplayAuthorDto.from(authorService.findAll());
    }

    @Override
    public DisplayAuthorDto create(CreateAuthorDto createAuthorDto) {
        Country country = countryRepository
                .findById(createAuthorDto.countryId())
                .orElseThrow(() -> new CountryNotFoundException(createAuthorDto.countryId()));

        return DisplayAuthorDto.from(authorService.create(createAuthorDto.toAuthor(country)));
    }

    @Override
    public Optional<DisplayAuthorDto> update(Long id, CreateAuthorDto createAuthorDto) {
        Country country = countryRepository
                .findById(createAuthorDto.countryId())
                .orElseThrow(() -> new CountryNotFoundException(createAuthorDto.countryId()));

        return authorService
                .update(id, createAuthorDto.toAuthor(country))
                .map(DisplayAuthorDto::from);
    }

    @Override
    public Optional<DisplayAuthorDto> deleteById(Long id) {
        return authorService
                .deleteById(id)
                .map(DisplayAuthorDto::from);
    }

    @Override
    public Optional<DisplayAuthorDetailsDto> findWithDetailsById(Long id) {
        return authorService
                .findById(id)
                .map(DisplayAuthorDetailsDto::from);
    }
}
