package by.sergo.book.app.mapper.genre;

import by.sergo.book.app.domain.dto.genre.GenreCreateUpdateRequestDto;
import by.sergo.book.app.domain.entity.Genre;
import by.sergo.book.app.mapper.CreateMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GenreCreateMapper implements CreateMapper<GenreCreateUpdateRequestDto, Genre> {
    @Override
    public Genre mapToEntity(GenreCreateUpdateRequestDto requestDto) {
        return Genre.builder()
                .name(requestDto.name())
                .build();
    }
}
