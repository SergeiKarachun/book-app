package by.sergo.book.app.bookclient.service;

import by.sergo.book.app.bookclient.domain.dto.book.BookCreateUpdateRequestDto;
import by.sergo.book.app.bookclient.domain.dto.book.BookResponseDto;
import by.sergo.book.app.bookclient.domain.entity.Book;
import by.sergo.book.app.bookclient.mapper.book.BookCreateMapper;
import by.sergo.book.app.bookclient.mapper.book.BookResponseMapper;
import by.sergo.book.app.bookclient.mapper.book.BookUpdateMapper;
import by.sergo.book.app.bookclient.repository.BookRepository;
import by.sergo.book.app.bookclient.service.exception.ExceptionMessageUtil;
import by.sergo.book.app.bookclient.service.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BookService {

    private final BookRepository bookRepository;
    private final BookCreateMapper bookCreateMapper;
    private final BookUpdateMapper bookUpdateMapper;
    private final BookResponseMapper bookResponseMapper;

    @Transactional
    public Optional<BookResponseDto> create(BookCreateUpdateRequestDto dto) {
        return Optional.of(bookCreateMapper.mapToEntity(dto))
                .map(bookRepository::saveAndFlush)
                .map(bookResponseMapper::mapToDto);
    }

    @Transactional
    public Optional<BookResponseDto> update(Long id, BookCreateUpdateRequestDto dto) {
        var book = getByIdOrElseThrow(id);

        return Optional.of(bookUpdateMapper.mapToEntity(dto, book))
                .map(bookRepository::saveAndFlush)
                .map(bookResponseMapper::mapToDto);
    }

    public Optional<BookResponseDto> getById(Long id) {
        return Optional.of(getByIdOrElseThrow(id))
                .map(bookResponseMapper::mapToDto);
    }

    public Optional<BookResponseDto> getByIsbn(Long isbn) {
        return bookRepository.findByIsbn(isbn)
                .map(bookResponseMapper::mapToDto);
    }

    public List<BookResponseDto> getAll() {
        return bookRepository.findAll()
                .stream()
                .map(bookResponseMapper::mapToDto)
                .toList();
    }

    @Transactional
    public boolean deleteById(Long id) {
        if (bookRepository.existsById(id)){
            bookRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Book getByIdOrElseThrow(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(
                        ExceptionMessageUtil.getNotFoundMessage("Book", "id", id)
                ));
    }
}
