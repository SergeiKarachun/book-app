package by.sergo.identityservice.mapper.user;


import by.sergo.identityservice.domain.dto.usercredential.UserUpdateRequestDto;
import by.sergo.identityservice.domain.entity.User;
import by.sergo.identityservice.mapper.UpdateMapper;
import org.springframework.stereotype.Component;

@Component
public class UserUpdateMapper implements UpdateMapper<UserUpdateRequestDto, User> {
    @Override
    public User mapToEntity(UserUpdateRequestDto dto, User user) {
        merge(dto, user);
        return user;
    }

    @Override
    public void merge(UserUpdateRequestDto dto, User user) {
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
    }
}
