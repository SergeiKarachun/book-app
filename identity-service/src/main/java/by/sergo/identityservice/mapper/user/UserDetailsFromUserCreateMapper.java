package by.sergo.identityservice.mapper.user;

import by.sergo.identityservice.domain.entity.UserDetails;
import by.sergo.identityservice.mapper.CreateMapper;
import by.sergo.identityservice.domain.dto.UserCredentialCreateRequestDto;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsFromUserCreateMapper implements CreateMapper<UserCredentialCreateRequestDto, UserDetails> {
    @Override
    public UserDetails mapToEntity(UserCredentialCreateRequestDto requestDto) {
        return UserDetails.builder()
                .name(requestDto.getName())
                .surname(requestDto.getSurname())
                .address(requestDto.getAddress())
                .phone(requestDto.getPhone())
                .build();
    }
}
