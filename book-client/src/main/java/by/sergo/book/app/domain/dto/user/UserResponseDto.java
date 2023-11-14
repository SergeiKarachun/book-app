package by.sergo.book.app.domain.dto.user;

import by.sergo.book.app.domain.dto.userdetails.UserDetailsResponseDto;
import by.sergo.book.app.domain.model.Role;
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
