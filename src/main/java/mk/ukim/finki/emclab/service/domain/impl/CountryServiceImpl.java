package mk.ukim.finki.emclab.service.domain.impl;

import mk.ukim.finki.emclab.model.domain.Author;
import mk.ukim.finki.emclab.model.domain.Country;
import mk.ukim.finki.emclab.repository.AuthorRepository;
import mk.ukim.finki.emclab.repository.CountryRepository;
import mk.ukim.finki.emclab.service.domain.AuthorService;
import mk.ukim.finki.emclab.service.domain.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;

    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public Optional<Country> findById(Long id) {
        return countryRepository.findById(id);
    }

    @Override
    public List<Country> findAll() {
        return countryRepository.findAll();
    }

    @Override
    public Country create(Country country) {
        return countryRepository.save(country);
    }

    @Override
    public Optional<Country> update(Long id, Country country) {
        return countryRepository
                .findById(id)
                .map((existingAuthor) -> {
                    existingAuthor.setName(country.getName());
                    existingAuthor.setContinent(country.getContinent());
                    return countryRepository.save(existingAuthor);
                });
    }

    @Override
    public Optional<Country> deleteById(Long id) {
        Optional<Country> country = countryRepository.findById(id);
        country.ifPresent(countryRepository::delete);
        return country;
    }
}
