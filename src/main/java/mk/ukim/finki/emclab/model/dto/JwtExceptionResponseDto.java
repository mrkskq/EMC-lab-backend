package mk.ukim.finki.emclab.model.dto;

import java.util.Date;

// lab3

public record JwtExceptionResponseDto(
        Date timestamp,
        int status,
        String error,
        String message,
        String path
) {
    public JwtExceptionResponseDto(int status, String error, String message, String path) {
        this(new Date(), status, error, message, path);
    }
}

