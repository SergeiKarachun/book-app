package by.sergo.identityservice.domain.dto;

import lombok.Value;


@Value
public class UserCredentialCreateRequestDto {
    String username;
    String email;
    String password;
    String name;
    String surname;
    String address;
    String phone;
}
