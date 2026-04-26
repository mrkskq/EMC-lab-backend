package mk.ukim.finki.emclab.service.application.impl;

import mk.ukim.finki.emclab.model.domain.Author;
import mk.ukim.finki.emclab.model.dto.CreateBookDto;
import mk.ukim.finki.emclab.model.dto.DisplayBookDetailsDto;
import mk.ukim.finki.emclab.model.dto.DisplayBookDto;
import mk.ukim.finki.emclab.model.exception.AuthorNotFoundException;
import mk.ukim.finki.emclab.service.application.BookApplicationService;
import mk.ukim.finki.emclab.service.domain.AuthorService;
import mk.ukim.finki.emclab.service.domain.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookApplicationServiceImpl implements BookApplicationService {

    private final BookService bookService;
    private final AuthorService authorService;

    public BookApplicationServiceImpl(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }

    @Override
    public Optional<DisplayBookDto> findById(Long id) {
        return bookService
                .findById(id)
                .map(DisplayBookDto::from);
    }

    @Override
    public List<DisplayBookDto> findAll() {
        return DisplayBookDto.from(bookService.findAll());
    }

    @Override
    public DisplayBookDto create(CreateBookDto createBookDto) {
        Author author = authorService
                .findById(createBookDto.authorId())
                .orElseThrow(() -> new AuthorNotFoundException(createBookDto.authorId()));

        return DisplayBookDto.from(bookService.create(createBookDto.toBook(author)));
    }

    @Override
    public Optional<DisplayBookDto> update(Long id, CreateBookDto createBookDto) {
        Author author = authorService
                .findById(createBookDto.authorId())
                .orElseThrow(() -> new AuthorNotFoundException(createBookDto.authorId()));

        return bookService
                .update(id, createBookDto.toBook(author))
                .map(DisplayBookDto::from);
    }

    @Override
    public Optional<DisplayBookDto> deleteById(Long id) {
        return bookService
                .deleteById(id)
                .map(DisplayBookDto::from);
    }

    @Override
    public Optional<DisplayBookDto> rent(Long id) {
        return bookService
                .rent(id)
                .map(DisplayBookDto::from);
    }

    @Override
    public Optional<DisplayBookDetailsDto> findWithDetailsById(Long id) {
        return bookService
                .findById(id)
                .map(DisplayBookDetailsDto::from);

    }
}
