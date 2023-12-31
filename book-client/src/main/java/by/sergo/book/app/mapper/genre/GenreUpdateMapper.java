package by.sergo.book.app.mapper.genre;

import by.sergo.book.app.domain.dto.genre.GenreCreateUpdateRequestDto;
import by.sergo.book.app.domain.entity.Genre;
import by.sergo.book.app.mapper.UpdateMapper;
import org.springframework.stereotype.Component;

@Component
public class GenreUpdateMapper implements UpdateMapper<GenreCreateUpdateRequestDto, Genre> {
    @Override
    public Genre mapToEntity(GenreCreateUpdateRequestDto dto, Genre entity) {
        merge(dto, entity);
        return entity;
    }

    @Override
    public void merge(GenreCreateUpdateRequestDto dto, Genre entity) {
        entity.setName(dto.name());
    }
}
