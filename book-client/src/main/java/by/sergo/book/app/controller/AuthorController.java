package by.sergo.book.app.controller;

import by.sergo.book.app.domain.dto.author.AuthorCreateUpdateRequestDto;
import by.sergo.book.app.domain.dto.author.AuthorResponseDto;
import by.sergo.book.app.service.AuthorService;
import by.sergo.book.app.service.exception.BadRequestException;
import by.sergo.book.app.service.exception.NotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/authors")
@RequiredArgsConstructor
@CrossOrigin
public class AuthorController {

    private final AuthorService authorService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create new author")
    public AuthorResponseDto create(@RequestBody AuthorCreateUpdateRequestDto dto) {
        return authorService.create(dto)
                .orElseThrow(() -> new BadRequestException("Can't create new author, please check input parameters"));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get author by id")
    public AuthorResponseDto getById(@PathVariable("id") Long id) {
        return authorService.getById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Author with id %s doesn't exist", id)));
    }

    @GetMapping
    @Operation(summary = "Get all authors")
    public List<AuthorResponseDto> getAll() {
        return authorService.getAll();
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Udate author")
    public AuthorResponseDto updateAuthor(@PathVariable("id") Long id,
                                          @RequestBody AuthorCreateUpdateRequestDto dto) {
        return authorService.update(id, dto)
                .orElseThrow(() -> new BadRequestException(HttpStatus.BAD_REQUEST, "Can't update author"));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete author")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        return authorService.deleteById(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
