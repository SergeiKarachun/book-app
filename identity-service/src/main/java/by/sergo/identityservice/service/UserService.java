package by.sergo.identityservice.service;


import by.sergo.identityservice.domain.dto.usercredential.UserCreateRequestDto;
import by.sergo.identityservice.domain.dto.usercredential.UserResponseDto;
import by.sergo.identityservice.domain.dto.usercredential.UserUpdateRequestDto;
import by.sergo.identityservice.domain.entity.User;
import by.sergo.identityservice.mapper.user.UserCreateMapper;
import by.sergo.identityservice.mapper.user.UserResponseMapper;
import by.sergo.identityservice.mapper.user.UserUpdateMapper;
import by.sergo.identityservice.repository.UserRepository;
import by.sergo.identityservice.service.exception.ExceptionMessageUtil;
import by.sergo.identityservice.service.exception.NotFoundException;
import jakarta.ws.rs.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        User user = getByIdOrElseThrow(id);

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
