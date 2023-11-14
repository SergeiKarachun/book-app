package by.sergo.book.app.domain.dto.author;

import lombok.Value;

@Value
public class AuthorCreateUpdateRequestDto {
    String name;
    String surname;
}
