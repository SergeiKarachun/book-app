package by.sergo.book.app.controller;

import by.sergo.book.app.domain.dto.genre.GenreCreateUpdateRequestDto;
import by.sergo.book.app.domain.dto.genre.GenreResponseDto;
import by.sergo.book.app.service.GenreService;
import by.sergo.book.app.service.exception.BadRequestException;
import by.sergo.book.app.service.exception.NotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/genres")
@RequiredArgsConstructor
public class GenreController {

    private final GenreService genreService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "create genre")
    public GenreResponseDto createGenre(@Parameter(required = true) @RequestBody GenreCreateUpdateRequestDto dto) {
        return genreService.create(dto)
                .orElseThrow(() -> new BadRequestException("Can't create genre, please check input parameter"));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get genre by id")
    public GenreResponseDto getById(@PathVariable("id") Long id) {
        return genreService.getById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Genre with id %s doesn't exist", id)));
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "update genre")
    public GenreResponseDto updateGenre(@PathVariable("id") Long id, @RequestBody GenreCreateUpdateRequestDto dto) {
        return genreService.update(id, dto)
                .orElseThrow(() -> new BadRequestException(HttpStatus.BAD_REQUEST, "Can't update genre, please check input parameter"));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete genre")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        return genreService.deleteById(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }

    @GetMapping
    @Operation(summary = "Get all genres")
    public List<GenreResponseDto> getAll() {
        return genreService.getAll();
    }
}
