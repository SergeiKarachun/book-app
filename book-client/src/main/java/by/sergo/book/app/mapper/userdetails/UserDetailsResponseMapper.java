package by.sergo.book.app.mapper.userdetails;

import by.sergo.book.app.domain.dto.userdetails.UserDetailsResponseDto;
import by.sergo.book.app.domain.entity.UserDetails;
import by.sergo.book.app.mapper.ResponseMapper;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsResponseMapper implements ResponseMapper<UserDetails, UserDetailsResponseDto> {
    @Override
    public UserDetailsResponseDto mapToDto(UserDetails entity) {
        return UserDetailsResponseDto.builder()
                .id(entity.getId())
                .userId(entity.getUser().getId())
                .name(entity.getName())
                .surname(entity.getSurname())
                .address(entity.getAddress())
                .phone(entity.getPhone())
                .build();
    }
}
