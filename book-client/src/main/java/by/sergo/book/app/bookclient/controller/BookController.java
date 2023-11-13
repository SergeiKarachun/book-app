package by.sergo.book.app.bookclient.controller;

import by.sergo.book.app.bookclient.domain.dto.book.BookCreateUpdateRequestDto;
import by.sergo.book.app.bookclient.domain.dto.book.BookResponseDto;
import by.sergo.book.app.bookclient.service.BookService;
import by.sergo.book.app.bookclient.service.exception.BadRequestException;
import by.sergo.book.app.bookclient.service.exception.NotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create new book")
    public BookResponseDto create(@RequestBody BookCreateUpdateRequestDto dto) {
        return bookService.create(dto)
                .orElseThrow(() -> new BadRequestException("Can't create book, please check input parameters"));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get book by id")
    public BookResponseDto getById(@PathVariable("id") Long id) {
        return bookService.getById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Book with id %s doesn't exist", id)));
    }

    @GetMapping("/{isbn}")
    @Operation(summary = "Get book by isbn")
    public BookResponseDto getByIsbn(@PathVariable("isbn") Long isbn) {
        return bookService.getByIsbn(isbn)
                .orElseThrow(() -> new NotFoundException(String.format("Book with id %s doesn't exist", isbn)));
    }

    @GetMapping
    @Operation(summary = "Get all books")
    public List<BookResponseDto> getAll() {
        return bookService.getAll();
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Update book")
    public BookResponseDto update(@PathVariable("id") Long id, @RequestBody BookCreateUpdateRequestDto dto) {
        return bookService.update(id, dto)
                .orElseThrow(() -> new BadRequestException(HttpStatus.BAD_REQUEST, "Can't update book, check input parameters"));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete book by id")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        return bookService.deleteById(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }


}
