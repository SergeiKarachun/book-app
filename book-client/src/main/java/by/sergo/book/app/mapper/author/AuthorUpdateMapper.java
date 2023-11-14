package by.sergo.book.app.mapper.author;

import by.sergo.book.app.domain.dto.author.AuthorCreateUpdateRequestDto;
import by.sergo.book.app.domain.entity.Author;
import by.sergo.book.app.mapper.UpdateMapper;
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
