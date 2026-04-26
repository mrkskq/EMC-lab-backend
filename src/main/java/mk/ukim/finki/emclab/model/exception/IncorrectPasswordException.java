package mk.ukim.finki.emclab.model.exception;

// lab3

public class IncorrectPasswordException extends RuntimeException {
    public IncorrectPasswordException() {
        super("The password is incorrect.");
    }
}

