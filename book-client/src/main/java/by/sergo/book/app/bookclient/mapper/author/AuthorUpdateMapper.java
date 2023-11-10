package by.sergo.book.app.bookclient.mapper.author;

import by.sergo.book.app.bookclient.domain.dto.author.AuthorCreateUpdateRequestDto;
import by.sergo.book.app.bookclient.domain.entity.Author;
import by.sergo.book.app.bookclient.mapper.UpdateMapper;
import org.springframework.stereotype.Component;

@Component
public class AuthorUpdateMapper implements UpdateMapper<AuthorCreateUpdateRequestDto, Author> {
    @Override
    public Author mapToEntity(AuthorCreateUpdateRequestDto dto, Author entity) {
        merge(dto, entity);
        return entity;
    }

    @Override
    public void merge(AuthorCreateUpdateRequestDto dto, Author entity) {
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
    }
}
