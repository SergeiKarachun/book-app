package by.sergo.book.app.mapper.user;

import by.sergo.book.app.domain.dto.user.UserCreateRequestDto;
import by.sergo.book.app.domain.entity.User;
import by.sergo.book.app.mapper.CreateMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserCreateMapper implements CreateMapper<UserCreateRequestDto, User> {

    private final UserDetailsFromUserCreateMapper userDetailsCreateMapper;
    @Override
    public User mapToEntity(UserCreateRequestDto requestDto) {
        var userDetails = userDetailsCreateMapper.mapToEntity(requestDto);
        var user = User.builder()
                .username(requestDto.getUsername())
                .email(requestDto.getEmail())
                .password(requestDto.getPassword())
                .build();
        userDetails.setUser(user);
        return user;
    }
}
