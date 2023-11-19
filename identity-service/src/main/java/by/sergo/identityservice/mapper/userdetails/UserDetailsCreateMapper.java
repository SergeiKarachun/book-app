package by.sergo.identityservice.mapper.userdetails;


import by.sergo.identityservice.domain.dto.userdetails.UserDetailsCreateRequestDto;
import by.sergo.identityservice.domain.entity.User;
import by.sergo.identityservice.domain.entity.UserDetails;
import by.sergo.identityservice.mapper.CreateMapper;
import by.sergo.identityservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserDetailsCreateMapper implements CreateMapper<UserDetailsCreateRequestDto, UserDetails> {

    private final UserRepository userRepository;

    @Override
    public UserDetails mapToEntity(UserDetailsCreateRequestDto requestDto) {
        var userDetails = UserDetails.builder()
                .name(requestDto.getName())
                .surname(requestDto.getSurname())
                .phone(requestDto.getPhone())
                .address(requestDto.getAddress())
                .build();

        var user = getUser(requestDto.getUserId());
        user.ifPresent(userDetails::setUser);

        return userDetails;
    }

    private Optional<User> getUser(Long userId) {
        return Optional.ofNullable(userId)
                .flatMap(userRepository::findById);
    }
}
