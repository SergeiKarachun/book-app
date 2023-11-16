package by.sergo.book.app.service;

import by.sergo.book.app.domain.dto.bookevent.BookEvent;
import by.sergo.book.app.domain.dto.idbook.IdBookCreateUpdateDto;
import by.sergo.book.app.domain.dto.idbook.IdBookResponseDto;
import by.sergo.book.app.domain.entity.IdBook;
import by.sergo.book.app.mapper.idbook.IdBookCreateMapper;
import by.sergo.book.app.mapper.idbook.IdBookResponseMapper;
import by.sergo.book.app.mapper.idbook.IdBookUpdateMapper;
import by.sergo.book.app.repository.IdBookRepository;
import by.sergo.book.app.service.exception.ExceptionMessageUtil;
import by.sergo.book.app.service.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class IdBookService {

    private final IdBookRepository idBookRepository;
    private final IdBookCreateMapper idBookCreateMapper;
    private final IdBookUpdateMapper idBookUpdateMapper;
    private final IdBookResponseMapper idBookResponseMapper;

    @Transactional
    public Optional<IdBookResponseDto> create(IdBookCreateUpdateDto dto) {
        return Optional.of(idBookCreateMapper.mapToEntity(dto))
                .map(idBookRepository::saveAndFlush)
                .map(idBookResponseMapper::mapToDto);
    }

    @Transactional
    @KafkaListener(topics = "book-event-topic", groupId = "book-event-group")
    public void processBookEvents(BookEvent bookEvent) {
        Long bookId = bookEvent.getBookId();
        if (bookEvent.getEventType().equals("CreateBook")) {
            var idBookCreateUpdateDto = new IdBookCreateUpdateDto(bookId);
            var idBookEntity = idBookCreateMapper.mapToEntity(idBookCreateUpdateDto);
            idBookRepository.saveAndFlush(idBookEntity);
        }

        if (bookEvent.getEventType().equals("DeleteBook")) {
            idBookRepository.deleteByBookId(bookEvent.getBookId());
        }
    }

    @Transactional
    public Optional<IdBookResponseDto> update(Long id, IdBookCreateUpdateDto dto) {
        var idBook = getByIdOrElseThrow(id);


        return Optional.of(idBookUpdateMapper.mapToEntity(dto, idBook))
                .map(idBookRepository::saveAndFlush)
                .map(idBookResponseMapper::mapToDto);
    }

    public Optional<IdBookResponseDto> getById(Long id) {
        return Optional.of(getByIdOrElseThrow(id))
                .map(idBookResponseMapper::mapToDto);
    }

    public List<IdBookResponseDto> getAll() {
        return idBookRepository.findAll()
                .stream()
                .map(idBookResponseMapper::mapToDto)
                .toList();
    }

    @Transactional
    public boolean deleteById(Long id) {
        if (idBookRepository.existsById(id)) {
            idBookRepository.deleteById(id);
            return true;
        }
        return false;
    }


    private IdBook getByIdOrElseThrow(Long id) {
        return idBookRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ExceptionMessageUtil.getNotFoundMessage("IdBook", "id", id)));
    }
}
