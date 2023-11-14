package by.sergo.book.app.domain.dto.userdetails;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UserDetailsResponseDto {
    Long id;
    Long userId;
    String name;
    String surname;
    String address;
    String phone;
}
