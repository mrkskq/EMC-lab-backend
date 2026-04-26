package mk.ukim.finki.emclab.service.application;


import mk.ukim.finki.emclab.model.dto.CreateAuthorDto;
import mk.ukim.finki.emclab.model.dto.DisplayAuthorDetailsDto;
import mk.ukim.finki.emclab.model.dto.DisplayAuthorDto;

import java.util.List;
import java.util.Optional;

public interface AuthorApplicationService {
    Optional<DisplayAuthorDto> findById(Long id);

    List<DisplayAuthorDto> findAll();

    DisplayAuthorDto create(CreateAuthorDto createAuthorDto);

    Optional<DisplayAuthorDto> update(Long id, CreateAuthorDto createAuthorDto);

    Optional<DisplayAuthorDto> deleteById(Long id);

    Optional<DisplayAuthorDetailsDto> findWithDetailsById(Long id);
}
