package mk.ukim.finki.emclab.model.exception;

public class BookNotAvailableException extends RuntimeException {
    public BookNotAvailableException(Long id) {
        super("Book with id %d is not available for renting".formatted(id));
    }
}
