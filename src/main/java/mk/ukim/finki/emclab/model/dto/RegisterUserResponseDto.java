package mk.ukim.finki.emclab.model.dto;

import mk.ukim.finki.emclab.model.domain.User;
import mk.ukim.finki.emclab.model.enumeration.Role;

// lab3

public record RegisterUserResponseDto(
        String username,
        String name,
        String surname,
        String email,
        Role role
) {
    public static RegisterUserResponseDto from(User user) {
        return new RegisterUserResponseDto(
                user.getUsername(),
                user.getName(),
                user.getSurname(),
                user.getEmail(),
                user.getRole()
        );
    }
}

