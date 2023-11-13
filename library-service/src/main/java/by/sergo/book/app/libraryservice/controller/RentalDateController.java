package by.sergo.book.app.libraryservice.controller;

import by.sergo.book.app.libraryservice.domain.dto.rentaldate.RentalDateCreateRequestDto;
import by.sergo.book.app.libraryservice.domain.dto.rentaldate.RentalDateResponseDto;
import by.sergo.book.app.libraryservice.domain.dto.rentaldate.RentalDateUpdateRequestDto;
import by.sergo.book.app.libraryservice.mapper.rentaldate.RentalDateCreateMapper;
import by.sergo.book.app.libraryservice.mapper.rentaldate.RentalDateResponseMapper;
import by.sergo.book.app.libraryservice.mapper.rentaldate.RentalDateUpdateMapper;
import by.sergo.book.app.libraryservice.service.RentalDateService;
import by.sergo.book.app.libraryservice.service.exception.BadRequestException;
import by.sergo.book.app.libraryservice.service.exception.NotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/rentaldates")
@RequiredArgsConstructor
public class RentalDateController {

    private final RentalDateService rentalDateService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "create rental date")
    public RentalDateResponseDto create(@RequestBody RentalDateCreateRequestDto dto) {
        return rentalDateService.create(dto)
                .orElseThrow(() -> new BadRequestException("Can't create rental date,  please check input parameters"));
    }

    @GetMapping("/{id}")
    @Operation(summary = "get rental date by id")
    public RentalDateResponseDto getById(@PathVariable("id") Long id) {
        return rentalDateService.getById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Rental date with id %s doesn't exist", id)));
    }

    @GetMapping
    @Operation(summary = "Get all rental dates")
    public List<RentalDateResponseDto> getAll() {
        return rentalDateService.getAll();
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "update rental date")
    public RentalDateResponseDto update(@PathVariable("id") Long id, @RequestBody RentalDateUpdateRequestDto dto) {
        return rentalDateService.update(id, dto)
                .orElseThrow(() -> new BadRequestException(HttpStatus.BAD_REQUEST, "Can't update rental date, please check input parameters"));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "delete rental date")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        return rentalDateService.deleteById(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }

}
