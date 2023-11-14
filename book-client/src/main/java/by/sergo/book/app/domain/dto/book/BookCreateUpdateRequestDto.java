package by.sergo.book.app.domain.dto.book;

import lombok.Value;

@Value
public class BookCreateUpdateRequestDto {
    Long isbn;
    String name;
    Long genreId;
    Long authorId;
    String description;
}
