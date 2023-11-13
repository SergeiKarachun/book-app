package by.sergo.book.app.bookclient.mapper.genre;

import by.sergo.book.app.bookclient.domain.dto.genre.GenreCreateUpdateRequestDto;
import by.sergo.book.app.bookclient.domain.entity.Genre;
import by.sergo.book.app.bookclient.mapper.CreateMapper;
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
