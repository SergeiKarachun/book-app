package by.sergo.book.app.domain.dto.userdetails;

import lombok.Value;

@Value
public class UserDetailsCreateRequestDto {
    Long userId;
    String name;
    String surname;
    String address;
    String phone;
}
