package by.sergo.book.app.bookclient.domain.dto.userdetails;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UserDetailsResponseDto {
    Long id;
    Long UserId;
    String name;
    String surname;
    String address;
    String phone;
}
