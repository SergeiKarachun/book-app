package by.sergo.book.app.service;

import by.sergo.book.app.domain.dto.book.BookCreateUpdateRequestDto;
import by.sergo.book.app.domain.dto.bookevent.BookEvent;
import by.sergo.book.app.domain.dto.book.BookResponseDto;
import by.sergo.book.app.domain.entity.Book;
import by.sergo.book.app.mapper.book.BookCreateMapper;
import by.sergo.book.app.mapper.book.BookResponseMapper;
import by.sergo.book.app.mapper.book.BookUpdateMapper;
import by.sergo.book.app.repository.BookRepository;
import by.sergo.book.app.service.exception.ExceptionMessageUtil;
import by.sergo.book.app.service.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
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
    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Transactional
    public Optional<BookResponseDto> create(BookCreateUpdateRequestDto dto) {

        var bookResponseDto = Optional.of(bookCreateMapper.mapToEntity(dto))
                .map(bookRepository::saveAndFlush)
                .map(bookResponseMapper::mapToDto);
        bookResponseDto.ifPresent(bookDto -> {
            BookEvent bookEvent = new BookEvent("CreateBook", bookDto.getId());
            kafkaTemplate.send("book-event-topic", bookEvent);
        });
        return bookResponseDto;
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
            bookRepository.deleteById(id);
            var orderEvent = BookEvent.builder()
                    .eventType("DeleteBook")
                    .bookId(id)
                    .build();
            kafkaTemplate.send("order-event-topic", orderEvent);
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
