package by.sergo.book.app.mapper.author;

import by.sergo.book.app.domain.dto.author.AuthorResponseDto;
import by.sergo.book.app.domain.entity.Author;
import by.sergo.book.app.mapper.ResponseMapper;
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
