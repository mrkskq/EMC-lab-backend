package mk.ukim.finki.emclab.model.exception;

public class BookNotFoundException extends RuntimeException{
    public BookNotFoundException(Long id){
        super("Book with id %d does not exist".formatted(id));
    }
}
