package by.sergo.identityservice.service;


import by.sergo.identityservice.domain.dto.userdetails.UserDetailsCreateRequestDto;
import by.sergo.identityservice.domain.dto.userdetails.UserDetailsResponseDto;
import by.sergo.identityservice.domain.dto.userdetails.UserDetailsUpdateRequestDto;
import by.sergo.identityservice.domain.entity.User;
import by.sergo.identityservice.domain.entity.UserDetails;
import by.sergo.identityservice.mapper.userdetails.UserDetailsCreateMapper;
import by.sergo.identityservice.mapper.userdetails.UserDetailsResponseMapper;
import by.sergo.identityservice.mapper.userdetails.UserDetailsUpdateMapper;
import by.sergo.identityservice.repository.UserDetailsRepository;
import by.sergo.identityservice.repository.UserRepository;
import by.sergo.identityservice.service.exception.ExceptionMessageUtil;
import by.sergo.identityservice.service.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserDetailsEntityService {

    private final UserRepository userRepository;
    private final UserDetailsRepository userDetailsRepository;
    private final UserDetailsCreateMapper userDetailsCreateMapper;
    private final UserDetailsUpdateMapper userDetailsUpdateMapper;
    private final UserDetailsResponseMapper userDetailsResponseMapper;

    @Transactional
    public Optional<UserDetailsResponseDto> create(UserDetailsCreateRequestDto dto) {
        var user = getUserByIdOrElseThrow(dto.getUserId());
        var userDetails = userDetailsCreateMapper.mapToEntity(dto);
        userDetails.setUser(user);

        return Optional.of(userDetails)
                .map(userDetailsRepository::saveAndFlush)
                .map(userDetailsResponseMapper::mapToDto);
    }

    @Transactional
    public Optional<UserDetailsResponseDto> update(Long id, UserDetailsUpdateRequestDto dto) {
        var userDetails = getByIdOrElseThrow(id);

        return Optional.of(userDetailsUpdateMapper.mapToEntity(dto, userDetails))
                .map(userDetailsRepository::saveAndFlush)
                .map(userDetailsResponseMapper::mapToDto);
    }

    public Optional<UserDetailsResponseDto> getById(Long id) {
        return Optional.of(getByIdOrElseThrow(id))
                .map(userDetailsResponseMapper::mapToDto);
    }

    public List<UserDetailsResponseDto> getAll() {
        return userDetailsRepository.findAll()
                .stream()
                .map(userDetailsResponseMapper::mapToDto)
                .toList();
    }

    @Transactional
    public Boolean deleteById(Long id) {
        if (userDetailsRepository.existsById(id)) {
            userDetailsRepository.deleteById(id);
            return true;
        }
        return false;
    }

    private UserDetails getByIdOrElseThrow(Long id) {
        return userDetailsRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ExceptionMessageUtil.getNotFoundMessage("UserDetails", "id", id)));
    }

    private User getUserByIdOrElseThrow(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(
                        ExceptionMessageUtil.getNotFoundMessage("User", "userId", userId)));
    }
}
