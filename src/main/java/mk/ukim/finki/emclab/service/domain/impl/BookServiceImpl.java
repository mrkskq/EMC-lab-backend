package mk.ukim.finki.emclab.service.domain.impl;

import mk.ukim.finki.emclab.model.domain.Book;
import mk.ukim.finki.emclab.model.dto.DisplayBookDto;
import mk.ukim.finki.emclab.model.dto.DisplayBookListDto;
import mk.ukim.finki.emclab.model.enumeration.BookCategory;
import mk.ukim.finki.emclab.model.enumeration.BookState;
import mk.ukim.finki.emclab.model.exception.BookNotAvailableException;
import mk.ukim.finki.emclab.repository.BookRepository;
import mk.ukim.finki.emclab.service.domain.BookService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Book create(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Optional<Book> update(Long id, Book book) {
        return bookRepository
                .findById(id)
                .map((existingBook) -> {
                    existingBook.setName(book.getName());
                    existingBook.setCategory(book.getCategory());
                    existingBook.setAuthor(book.getAuthor());
                    existingBook.setState(book.getState());
                    existingBook.setAvailableCopies(book.getAvailableCopies());
                    return bookRepository.save(existingBook);
                });
    }

    @Override
    public Optional<Book> deleteById(Long id) {
        Optional<Book> book = bookRepository.findById(id);
        book.ifPresent(bookRepository::delete);
        return book;
    }

    @Override
    @Transactional
    public Optional<Book> rent(Long id) {
        return bookRepository
                .findById(id)
                .map(book -> {
                    if (book.getState() == BookState.BAD){
                        throw new BookNotAvailableException(id);
                    }
                    if (book.getAvailableCopies() == 0){
                        throw new BookNotAvailableException(id);
                    }
                    book.setAvailableCopies(book.getAvailableCopies() - 1);
                    return bookRepository.save(book);
                });
    }



    // lab2 - 1. za pagination
    @Override
    public Page<DisplayBookListDto> findAll(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return bookRepository.findAll(pageable).map(DisplayBookListDto::from);
    }


    @Override
    public Page<DisplayBookListDto> listBooks(BookCategory category, BookState state, String authorName, Boolean available, int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));

        int availableCopies = available != null && available ? 1 : 0;

        Page<Book> books = bookRepository
                .findByCategoryAndStateAndAuthorNameContainingAndAvailableCopiesGreaterThan(
                        category, state, authorName != null ? authorName : "", availableCopies, pageable);

        return books.map(DisplayBookListDto::from);
    }

    @Override
    public List<Book> findTop10NewestBooks() {
        return bookRepository
                .findTop10ByDatePublishedBefore(LocalDate.now())
                .stream()
                .toList();
    }
}
