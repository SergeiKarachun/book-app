package by.sergo.identityservice.domain.dto.userdetails;

import lombok.Value;

@Value
public class UserDetailsUpdateRequestDto {
    String name;
    String surname;
    String address;
    String phone;
}
