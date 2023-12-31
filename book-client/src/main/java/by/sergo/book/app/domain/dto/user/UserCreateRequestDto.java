package by.sergo.book.app.domain.dto.user;

import lombok.Value;

@Value
public class UserCreateRequestDto {
    String username;
    String email;
    String password;
    String name;
    String surname;
    String address;
    String phone;
}
