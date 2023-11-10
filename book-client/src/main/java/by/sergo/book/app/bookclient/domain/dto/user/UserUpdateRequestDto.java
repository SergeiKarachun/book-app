package by.sergo.book.app.bookclient.domain.dto.user;

import lombok.Value;

@Value
public class UserUpdateRequestDto {
    String username;
    String email;
}
