package mk.ukim.finki.emclab.web.controller;

import jakarta.validation.Valid;
import mk.ukim.finki.emclab.model.domain.Book;
import mk.ukim.finki.emclab.model.dto.CreateBookDto;
import mk.ukim.finki.emclab.model.dto.DisplayBookDetailsDto;
import mk.ukim.finki.emclab.model.dto.DisplayBookDto;
import mk.ukim.finki.emclab.model.dto.DisplayBookListDto;
import mk.ukim.finki.emclab.model.enumeration.BookCategory;
import mk.ukim.finki.emclab.model.enumeration.BookState;
import mk.ukim.finki.emclab.model.projection.BookDetailedProjection;
import mk.ukim.finki.emclab.model.projection.BookShortProjection;
import mk.ukim.finki.emclab.repository.BookRepository;
import mk.ukim.finki.emclab.service.application.BookApplicationService;
import mk.ukim.finki.emclab.service.domain.BookService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookApplicationService bookApplicationService;
    private final BookService bookService;
    private final BookRepository bookRepository;

    public BookController(BookApplicationService bookApplicationService, BookService bookService, BookRepository bookRepository) {
        this.bookApplicationService = bookApplicationService;
        this.bookService = bookService;
        this.bookRepository = bookRepository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<DisplayBookDto> findById(@PathVariable Long id){
        return bookApplicationService
                .findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/details")
    public ResponseEntity<DisplayBookDetailsDto> findWithDetailsById(@PathVariable Long id) {
        return bookApplicationService
                .findWithDetailsById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<DisplayBookDto>> findAll(){
        return ResponseEntity.ok(bookApplicationService.findAll());
    }

    @PostMapping("/add")
    public ResponseEntity<DisplayBookDto> create(@RequestBody @Valid CreateBookDto createBookDto){
        return ResponseEntity.ok(bookApplicationService.create(createBookDto));
    }

    @PutMapping("/{id}/edit")
    public ResponseEntity<DisplayBookDto> update(
            @PathVariable Long id,
            @RequestBody @Valid CreateBookDto createBookDto
    ) {
        return bookApplicationService
                .update(id, createBookDto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<DisplayBookDto> deleteById(@PathVariable Long id){
        return bookApplicationService
                .deleteById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}/rent")
    public ResponseEntity<DisplayBookDto> rent (@PathVariable Long id){
        return bookApplicationService
                .rent(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    // lab2 - 1. za pagination
    @GetMapping("/paginated")
    public ResponseEntity<Page<DisplayBookListDto>> findAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "name") String sortBy
    ) {
        return ResponseEntity.ok(bookService.findAll(page, size, sortBy));
    }


    @GetMapping("/list-books")
    public Page<DisplayBookListDto> listBooks(
            @RequestParam(required = false) BookCategory category,
            @RequestParam(required = false) BookState state,
            @RequestParam(required = false) String authorName,
            @RequestParam(required = false) Boolean available,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "name") String sortBy
    ) {
        return bookService.listBooks(category, state, authorName, available, page, size, sortBy);
    }

    // lab2 - 2. za projections
    @GetMapping("/short")
    public List<BookShortProjection> getShortBooks(){
        return bookRepository.findAllShortBy();
    }

    // lab2 - 2. za projections
    @GetMapping("/detailed")
    public List<BookDetailedProjection> getDetailedBooks() {
        return bookRepository.findAllDetailedBy();
    }


    // lab2 - 3. za entity graph
    /*
    OUTPUT:

    select
    b1_0.id, b1_0.author_id,
    a1_0.id, a1_0.country_id,
    c1_0.id, c1_0.continent, c1_0.name,
    a1_0.created_at, a1_0.name, a1_0.surname, a1_0.updated_at,
    b1_0.available_copies, b1_0.category, b1_0.created_at, b1_0.name, b1_0.state, b1_0.updated_at
    from books b1_0
    join authors a1_0 on a1_0.id = b1_0.author_id
    join countries c1_0 on c1_0.id = a1_0.country_id
     */
    @GetMapping("/entity-graph")
    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    // lab4
    @GetMapping("/states")
    public List<String> getStates() {
        return Arrays.stream(BookState.values())
                .map(Enum::name)
                .toList();
    }

    // lab4
    @GetMapping("/categories")
    public List<String> getCategories() {
        return Arrays.stream(BookCategory.values())
                .map(Enum::name)
                .toList();
    }
}
