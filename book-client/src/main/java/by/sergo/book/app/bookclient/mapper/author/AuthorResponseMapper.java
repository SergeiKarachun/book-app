package by.sergo.book.app.bookclient.mapper.author;

import by.sergo.book.app.bookclient.domain.dto.author.AuthorResponseDto;
import by.sergo.book.app.bookclient.domain.entity.Author;
import by.sergo.book.app.bookclient.mapper.ResponseMapper;
import org.springframework.stereotype.Component;

@Component
public class AuthorResponseMapper implements ResponseMapper<Author, AuthorResponseDto> {
    @Override
    public AuthorResponseDto mapToDto(Author entity) {
        return AuthorResponseDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .surname(entity.getSurname())
                .build();
    }
}
