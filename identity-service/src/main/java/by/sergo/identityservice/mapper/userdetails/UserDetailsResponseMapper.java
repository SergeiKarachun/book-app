package by.sergo.identityservice.mapper.userdetails;


import by.sergo.identityservice.domain.dto.userdetails.UserDetailsResponseDto;
import by.sergo.identityservice.domain.entity.UserDetails;
import by.sergo.identityservice.mapper.ResponseMapper;
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
