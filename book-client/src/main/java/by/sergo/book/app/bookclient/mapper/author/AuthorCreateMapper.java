package by.sergo.book.app.bookclient.mapper.author;

import by.sergo.book.app.bookclient.domain.dto.author.AuthorCreateUpdateRequestDto;
import by.sergo.book.app.bookclient.domain.entity.Author;
import by.sergo.book.app.bookclient.mapper.CreateMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthorCreateMapper implements CreateMapper<AuthorCreateUpdateRequestDto, Author> {

    @Override
    public Author mapToEntity(AuthorCreateUpdateRequestDto requestDto) {
        return Author.builder()
                .name(requestDto.getName())
                .surname(requestDto.getSurname())
                .build();
    }
}
