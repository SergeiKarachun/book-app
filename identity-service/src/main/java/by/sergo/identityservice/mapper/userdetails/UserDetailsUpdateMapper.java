package by.sergo.identityservice.mapper.userdetails;


import by.sergo.identityservice.domain.dto.userdetails.UserDetailsUpdateRequestDto;
import by.sergo.identityservice.domain.entity.UserDetails;
import by.sergo.identityservice.mapper.UpdateMapper;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsUpdateMapper implements UpdateMapper<UserDetailsUpdateRequestDto, UserDetails> {
    @Override
    public UserDetails mapToEntity(UserDetailsUpdateRequestDto dto, UserDetails entity) {
        merge(dto, entity);
        return entity;
    }

    @Override
    public void merge(UserDetailsUpdateRequestDto dto, UserDetails entity) {
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setAddress(dto.getAddress());
        entity.setPhone(dto.getPhone());
    }
}
