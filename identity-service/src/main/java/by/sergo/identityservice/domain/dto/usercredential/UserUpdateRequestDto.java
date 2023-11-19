package by.sergo.identityservice.domain.dto.usercredential;

import lombok.Value;

@Value
public class UserUpdateRequestDto {
    String username;
    String email;
}
