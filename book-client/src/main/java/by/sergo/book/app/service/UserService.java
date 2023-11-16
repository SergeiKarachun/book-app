package by.sergo.book.app.service;

import by.sergo.book.app.domain.dto.user.UserCreateRequestDto;
import by.sergo.book.app.domain.dto.user.UserResponseDto;
import by.sergo.book.app.domain.dto.user.UserUpdateRequestDto;
import by.sergo.book.app.domain.entity.User;
import by.sergo.book.app.mapper.user.UserCreateMapper;
import by.sergo.book.app.mapper.user.UserResponseMapper;
import by.sergo.book.app.mapper.user.UserUpdateMapper;
import by.sergo.book.app.repository.UserRepository;
import by.sergo.book.app.service.exception.BadRequestException;
import by.sergo.book.app.service.exception.ExceptionMessageUtil;
import by.sergo.book.app.service.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;
    private final UserCreateMapper userCreateMapper;
    private final UserResponseMapper userResponseMapper;
    private final UserUpdateMapper userUpdateMapper;

    @Transactional
    public Optional<UserResponseDto> create(UserCreateRequestDto dto) {
        checkEmailIsUnique(dto.getEmail());
        checkUsernameIsUnique(dto.getUsername());

        return Optional.of(userCreateMapper.mapToEntity(dto))
                .map(userRepository::save)
                .map(userResponseMapper::mapToDto);
    }

    @Transactional
    public Optional<UserResponseDto> update(Long id, UserUpdateRequestDto dto) {
        var user = getByIdOrElseThrow(id);

        if (!user.getEmail().equals(dto.getEmail())) {
            checkEmailIsUnique(dto.getEmail());
        }

        if (!user.getUsername().equals(dto.getUsername())) {
            checkUsernameIsUnique(dto.getUsername());
        }

        return Optional.of(userUpdateMapper.mapToEntity(dto, user))
                .map(userRepository::saveAndFlush)
                .map(userResponseMapper::mapToDto);
    }

    public Optional<UserResponseDto> getById(Long id) {
        return Optional.of(getByIdOrElseThrow(id))
                .map(userResponseMapper::mapToDto);
    }

    public List<UserResponseDto> getAll() {
        return userRepository.findAll()
                .stream()
                .map(userResponseMapper::mapToDto)
                .toList();
    }

    @Transactional
    public boolean deleteById(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }


    private void checkUsernameIsUnique(String username) {
        if (userRepository.existsByUsername(username)) {
            throw new BadRequestException(ExceptionMessageUtil.getAlreadyExistsMessage("User", "username",  username));
        }
    }

    private void checkEmailIsUnique(String email) {
        if (userRepository.existsByEmail(email)) {
            throw new BadRequestException(ExceptionMessageUtil.getAlreadyExistsMessage("User", "email", email));
        }
    }

    public User getByIdOrElseThrow(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ExceptionMessageUtil.getNotFoundMessage("User", "id", id)));
    }
}
