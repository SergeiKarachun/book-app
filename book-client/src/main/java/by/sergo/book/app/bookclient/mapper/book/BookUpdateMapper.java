package by.sergo.book.app.bookclient.mapper.book;

import by.sergo.book.app.bookclient.domain.dto.book.BookCreateUpdateRequestDto;
import by.sergo.book.app.bookclient.domain.entity.Author;
import by.sergo.book.app.bookclient.domain.entity.Book;
import by.sergo.book.app.bookclient.domain.entity.Genre;
import by.sergo.book.app.bookclient.mapper.UpdateMapper;
import by.sergo.book.app.bookclient.repository.AuthorRepository;
import by.sergo.book.app.bookclient.repository.GenreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class BookUpdateMapper implements UpdateMapper<BookCreateUpdateRequestDto, Book> {

    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;

    @Override
    public Book mapToEntity(BookCreateUpdateRequestDto dto, Book entity) {
        merge(dto, entity);
        return entity;
    }

    @Override
    public void merge(BookCreateUpdateRequestDto dto, Book entity) {
        entity.setIsbn(dto.getIsbn());
        entity.setName(dto.getName());
        entity.setGenre(getGenre(dto.getGenreId()));
        entity.setAuthor(getAuthor(dto.getAuthorId()));
        entity.setDescription(dto.getDescription());
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
