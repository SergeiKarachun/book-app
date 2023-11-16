package by.sergo.identityservice.mapper.user;

import by.sergo.identityservice.mapper.CreateMapper;
import by.sergo.identityservice.domain.dto.UserCredentialCreateRequestDto;
import by.sergo.identityservice.domain.entity.UserCredential;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserCreateMapper implements CreateMapper<UserCredentialCreateRequestDto, UserCredential> {

    private final UserDetailsFromUserCreateMapper userDetailsCreateMapper;
    private final PasswordEncoder passwordEncoder;
    @Override
    public UserCredential mapToEntity(UserCredentialCreateRequestDto requestDto) {
        var userDetails = userDetailsCreateMapper.mapToEntity(requestDto);
        var user = UserCredential.builder()
                .username(requestDto.getUsername())
                .email(requestDto.getEmail())
                .password(passwordEncoder.encode(requestDto.getPassword()))
                .build();
        userDetails.setUser(user);
        return user;
    }
}
