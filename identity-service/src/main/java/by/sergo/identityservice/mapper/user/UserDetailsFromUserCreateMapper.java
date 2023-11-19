package by.sergo.identityservice.mapper.user;

import by.sergo.identityservice.domain.entity.UserDetails;
import by.sergo.identityservice.mapper.CreateMapper;
import by.sergo.identityservice.domain.dto.usercredential.UserCreateRequestDto;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsFromUserCreateMapper implements CreateMapper<UserCreateRequestDto, UserDetails> {
    @Override
    public UserDetails mapToEntity(UserCreateRequestDto requestDto) {
        return UserDetails.builder()
                .name(requestDto.getName())
                .surname(requestDto.getSurname())
                .address(requestDto.getAddress())
                .phone(requestDto.getPhone())
                .build();
    }
}
