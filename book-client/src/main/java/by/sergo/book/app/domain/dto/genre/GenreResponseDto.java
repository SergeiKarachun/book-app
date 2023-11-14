package by.sergo.book.app.domain.dto.genre;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class GenreResponseDto {
    Long id;
    String name;
}
