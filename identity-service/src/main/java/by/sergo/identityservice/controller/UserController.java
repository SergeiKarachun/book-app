package by.sergo.identityservice.controller;


import by.sergo.identityservice.domain.dto.usercredential.UserCreateRequestDto;
import by.sergo.identityservice.domain.dto.usercredential.UserResponseDto;
import by.sergo.identityservice.domain.dto.usercredential.UserUpdateRequestDto;
import by.sergo.identityservice.service.UserService;
import by.sergo.identityservice.service.exception.BadRequestException;
import by.sergo.identityservice.service.exception.NotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@CrossOrigin
public class UserController {

    private final UserService userService;

    @GetMapping("/{id}")
    @Operation(summary = "Get user by id")
    public UserResponseDto getById(@PathVariable("id") Long id) {
        return userService.getById(id)
                .orElseThrow(() -> new NotFoundException(String.format("User with id %s doesn't exist")));
    }

    @GetMapping
    @Operation(summary = "Get all users")
    public List<UserResponseDto> getAll() {
        return userService.getAll();
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Update user")
    public UserResponseDto update(@PathVariable("id") Long id, @RequestBody UserUpdateRequestDto dto) {
        return userService.update(id, dto)
                .orElseThrow(() -> new BadRequestException(HttpStatus.BAD_REQUEST, "Can't update user, check input parameters"));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete user")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        return userService.deleteById(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }

}
