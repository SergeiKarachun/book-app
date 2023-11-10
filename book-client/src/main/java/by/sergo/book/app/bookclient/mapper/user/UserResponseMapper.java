package by.sergo.book.app.bookclient.mapper.user;

import by.sergo.book.app.bookclient.domain.dto.user.UserResponseDto;
import by.sergo.book.app.bookclient.domain.dto.userdetails.UserDetailsResponseDto;
import by.sergo.book.app.bookclient.domain.entity.User;
import by.sergo.book.app.bookclient.mapper.ResponseMapper;
import org.springframework.stereotype.Component;

@Component
public class UserResponseMapper implements ResponseMapper<User, UserResponseDto> {
    @Override
    public UserResponseDto mapToDto(User user) {
        return UserResponseDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .role(user.getRole())
                .userDetailsDto(UserDetailsResponseDto.builder()
                        .id(user.getUserDetails().getId())
                        .userId(user.getId())
                        .name(user.getUserDetails().getName())
                        .surname(user.getUserDetails().getSurname())
                        .address(user.getUserDetails().getAddress())
                        .phone(user.getUserDetails().getPhone())
                        .build())
                .build();
    }
}
