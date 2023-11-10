package by.sergo.book.app.bookclient.mapper.user;

import by.sergo.book.app.bookclient.domain.dto.user.UserCreateRequestDto;
import by.sergo.book.app.bookclient.domain.entity.UserDetails;
import by.sergo.book.app.bookclient.mapper.CreateMapper;
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
