package by.sergo.identityservice.mapper.user;

import by.sergo.identityservice.mapper.CreateMapper;
import by.sergo.identityservice.domain.dto.usercredential.UserCreateRequestDto;
import by.sergo.identityservice.domain.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserCreateMapper implements CreateMapper<UserCreateRequestDto, User> {

    private final UserDetailsFromUserCreateMapper userDetailsCreateMapper;
    private final PasswordEncoder passwordEncoder;
    @Override
    public User mapToEntity(UserCreateRequestDto requestDto) {
        var userDetails = userDetailsCreateMapper.mapToEntity(requestDto);
        var user = User.builder()
                .username(requestDto.getUsername())
                .email(requestDto.getEmail())
                .password(passwordEncoder.encode(requestDto.getPassword()))
                .build();
        userDetails.setUser(user);
        return user;
    }
}
