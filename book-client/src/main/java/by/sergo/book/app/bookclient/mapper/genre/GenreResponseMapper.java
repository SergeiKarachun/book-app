package by.sergo.book.app.bookclient.mapper.genre;

import by.sergo.book.app.bookclient.domain.dto.genre.GenreResponseDto;
import by.sergo.book.app.bookclient.domain.entity.Genre;
import by.sergo.book.app.bookclient.mapper.ResponseMapper;
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
