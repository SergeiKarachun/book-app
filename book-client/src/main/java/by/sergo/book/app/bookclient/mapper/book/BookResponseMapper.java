package by.sergo.book.app.bookclient.mapper.book;

import by.sergo.book.app.bookclient.domain.dto.book.BookResponseDto;
import by.sergo.book.app.bookclient.domain.entity.Book;
import by.sergo.book.app.bookclient.mapper.ResponseMapper;
import org.springframework.stereotype.Component;

@Component
public class BookResponseMapper implements ResponseMapper<Book, BookResponseDto> {
    @Override
    public BookResponseDto mapToDto(Book book) {
        return BookResponseDto.builder()
                .id(book.getId())
                .isbn(book.getIsbn())
                .name(book.getName())
                .genre(book.getGenre().getName())
                .description(book.getDescription())
                .author(book.getAuthor().getSurname() + book.getAuthor().getName())
                .build();
    }
}
