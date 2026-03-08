package mk.ukim.finki.emclab.model.exception;

public class CountryNotFoundException extends RuntimeException {
    public CountryNotFoundException(Long id) {
        super("The country with id %d does not exist".formatted(id));
    }
}
