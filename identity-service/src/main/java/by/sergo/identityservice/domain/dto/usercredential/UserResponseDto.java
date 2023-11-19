package by.sergo.identityservice.domain.dto.usercredential;

import by.sergo.identityservice.domain.dto.userdetails.UserDetailsResponseDto;
import by.sergo.identityservice.domain.model.Role;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UserResponseDto {
    Long id;
    String username;
    String email;
    Role role;
    UserDetailsResponseDto userDetailsDto;
}
