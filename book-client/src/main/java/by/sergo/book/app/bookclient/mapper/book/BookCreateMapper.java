package by.sergo.book.app.bookclient.mapper.book;

import by.sergo.book.app.bookclient.domain.dto.book.BookCreateUpdateRequestDto;
import by.sergo.book.app.bookclient.domain.entity.Author;
import by.sergo.book.app.bookclient.domain.entity.Book;
import by.sergo.book.app.bookclient.domain.entity.Genre;
import by.sergo.book.app.bookclient.mapper.CreateMapper;
import by.sergo.book.app.bookclient.repository.AuthorRepository;
import by.sergo.book.app.bookclient.repository.GenreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class BookCreateMapper implements CreateMapper<BookCreateUpdateRequestDto, Book> {

    private final GenreRepository genreRepository;
    private final AuthorRepository authorRepository;
    @Override
    public Book mapToEntity(BookCreateUpdateRequestDto requestDto) {
        var genre = getGenre(requestDto.getGenreId());
        var author = getAuthor(requestDto.getAuthorId());

        var book = Book.builder()
                .isbn(requestDto.getIsbn())
                .name(requestDto.getName())
                .description(requestDto.getDescription())
                .build();
        book.setAuthor(author);
        book.setGenre(genre);

        return book;
    }

    private Author getAuthor(Long authorId) {
        return Optional.ofNullable(authorId)
                .flatMap(authorRepository::findById)
                .orElse(null);
    }

    private Genre getGenre(Long genreId) {
        return Optional.ofNullable(genreId)
                .flatMap(genreRepository::findById)
                .orElse(null);
    }
}
