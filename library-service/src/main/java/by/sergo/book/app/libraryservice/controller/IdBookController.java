package by.sergo.book.app.libraryservice.controller;

import by.sergo.book.app.libraryservice.domain.dto.idbook.IdBookCreateUpdateDto;
import by.sergo.book.app.libraryservice.domain.dto.idbook.IdBookResponseDto;
import by.sergo.book.app.libraryservice.service.IdBookService;
import by.sergo.book.app.libraryservice.service.exception.NotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.ws.rs.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/idbooks")
@RequiredArgsConstructor
public class IdBookController {

    private final IdBookService idBookService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create new idbook")
    public IdBookResponseDto  create(@RequestBody IdBookCreateUpdateDto dto) {
        return idBookService.create(dto)
                .orElseThrow(() -> new BadRequestException("Can't create new idbook, please check input parameters"));
    }

    @GetMapping("/{id}")
    @Operation(summary = "get idBook by id")
    public IdBookResponseDto getById(@PathVariable("id") Long id) {
        return idBookService.getById(id)
                .orElseThrow(() -> new NotFoundException(String.format("IdBook with id %s doesn't exist", id)));
    }

    @PutMapping(value = "/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Update idBook")
    public IdBookResponseDto update(@PathVariable("id") Long id, @RequestBody IdBookCreateUpdateDto dto) {
        return idBookService.update(id, dto)
                .orElseThrow(() -> new by.sergo.book.app.libraryservice.service.exception.BadRequestException(HttpStatus.BAD_REQUEST, "Can't update idBook, please check input parameters"));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> deleteById(@PathVariable("id") Long id) {
        return idBookService.deleteById(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}

