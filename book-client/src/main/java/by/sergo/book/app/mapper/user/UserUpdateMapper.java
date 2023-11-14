package by.sergo.book.app.mapper.user;

import by.sergo.book.app.domain.dto.user.UserUpdateRequestDto;
import by.sergo.book.app.domain.entity.User;
import by.sergo.book.app.mapper.UpdateMapper;
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
