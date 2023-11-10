package by.sergo.book.app.bookclient.domain.dto.book;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class BookResponseDto {
    Long id;
    Long isbn;
    String name;
    String genre;
    String author;
    String description;
}
