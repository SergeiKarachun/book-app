package by.sergo.book.app.mapper.genre;

import by.sergo.book.app.domain.dto.genre.GenreResponseDto;
import by.sergo.book.app.domain.entity.Genre;
import by.sergo.book.app.mapper.ResponseMapper;
import org.springframework.stereotype.Component;

@Component
public class GenreResponseMapper implements ResponseMapper<Genre, GenreResponseDto> {
    @Override
    public GenreResponseDto mapToDto(Genre genre) {
        return GenreResponseDto.builder()
                .id(genre.getId())
                .name(genre.getName())
                .build();
    }
}
