package by.sergo.book.app.controller;

import by.sergo.book.app.domain.dto.userdetails.UserDetailsCreateRequestDto;
import by.sergo.book.app.domain.dto.userdetails.UserDetailsResponseDto;
import by.sergo.book.app.domain.dto.userdetails.UserDetailsUpdateRequestDto;
import by.sergo.book.app.service.UserDetailsService;
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
@RequestMapping("/api/v1/userdetails")
@RequiredArgsConstructor
public class UserDetailsController {

    private final UserDetailsService userDetailsService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create new user details")
    public UserDetailsResponseDto create(@RequestBody UserDetailsCreateRequestDto dto) {
        return userDetailsService.create(dto)
                .orElseThrow(() -> new BadRequestException("Can't create user details, please check input parameters"));
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Update user details")
    public UserDetailsResponseDto update(@PathVariable("id") Long id, @RequestBody UserDetailsUpdateRequestDto dto) {
        return userDetailsService.update(id, dto)
                .orElseThrow(() -> new BadRequestException(HttpStatus.BAD_REQUEST, "Can't update details, please check input parameters"));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete user details")
    public ResponseEntity<?> delete (@PathVariable("id") Long id) {
        return userDetailsService.deleteById(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get user details by id")
    public UserDetailsResponseDto getById(@PathVariable("id") Long id) {
        return userDetailsService.getById(id)
                .orElseThrow(() -> new NotFoundException(String.format("User details with id %s doesn't exist", id)));
    }

    @GetMapping()
    @Operation(summary = "Get all user details")
    public List<UserDetailsResponseDto> getAll() {
        return userDetailsService.getAll();
    }
}
