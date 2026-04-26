package mk.ukim.finki.emclab.service.application;

import mk.ukim.finki.emclab.model.dto.LoginUserRequestDto;
import mk.ukim.finki.emclab.model.dto.LoginUserResponseDto;
import mk.ukim.finki.emclab.model.dto.RegisterUserRequestDto;
import mk.ukim.finki.emclab.model.dto.RegisterUserResponseDto;

import java.util.Optional;

// lab3

public interface UserApplicationService {
    Optional<RegisterUserResponseDto> register(RegisterUserRequestDto registerUserRequestDto);

    Optional<LoginUserResponseDto> login(LoginUserRequestDto loginUserRequestDto);

    Optional<RegisterUserResponseDto> findByUsername(String username);
}

