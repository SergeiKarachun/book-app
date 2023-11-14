package by.sergo.book.app.domain.dto.author;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class AuthorResponseDto {
    Long id;
    String name;
    String surname;
}
