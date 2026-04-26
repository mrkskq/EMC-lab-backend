package mk.ukim.finki.emclab.service.application.impl;

import mk.ukim.finki.emclab.helpers.JwtHelper;
import mk.ukim.finki.emclab.model.domain.User;
import mk.ukim.finki.emclab.model.dto.LoginUserRequestDto;
import mk.ukim.finki.emclab.model.dto.LoginUserResponseDto;
import mk.ukim.finki.emclab.model.dto.RegisterUserRequestDto;
import mk.ukim.finki.emclab.model.dto.RegisterUserResponseDto;
import mk.ukim.finki.emclab.service.application.UserApplicationService;
import mk.ukim.finki.emclab.service.domain.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

// lab3

@Service
public class UserApplicationServiceImpl implements UserApplicationService {
    private final UserService userService;
    private final JwtHelper jwtHelper;

    public UserApplicationServiceImpl(UserService userService, JwtHelper jwtHelper) {
        this.userService = userService;
        this.jwtHelper = jwtHelper;
    }

    @Override
    public Optional<RegisterUserResponseDto> register(RegisterUserRequestDto registerUserRequestDto) {
        User user = userService.register(registerUserRequestDto.toUser());
        RegisterUserResponseDto displayUserDto = RegisterUserResponseDto.from(user);
        return Optional.of(displayUserDto);
    }

    @Override
    public Optional<LoginUserResponseDto> login(LoginUserRequestDto loginUserRequestDto) {
        User user = userService.login(loginUserRequestDto.username(), loginUserRequestDto.password());

        String token = jwtHelper.generateToken(user);

        return Optional.of(new LoginUserResponseDto(token));
    }

    @Override
    public Optional<RegisterUserResponseDto> findByUsername(String username) {
        return userService
                .findByUsername(username)
                .map(RegisterUserResponseDto::from);
    }
}

