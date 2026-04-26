package mk.ukim.finki.emclab.model.dto;

import mk.ukim.finki.emclab.model.domain.User;

// lab3

public record RegisterUserRequestDto(
        String name,
        String surname,
        String email,
        String username,
        String password
) {
    public User toUser() {
        return new User(name, surname, email, username, password);
    }
}

