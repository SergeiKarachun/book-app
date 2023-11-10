package by.sergo.book.app.bookclient.domain.dto.book;

import lombok.Value;

@Value
public class BookCreateUpdateRequestDto {
    Long isbn;
    String name;
    Long genreId;
    Long authorId;
    String description;
}
