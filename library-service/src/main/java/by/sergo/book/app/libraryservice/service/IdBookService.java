package by.sergo.book.app.libraryservice.service;

import by.sergo.book.app.libraryservice.domain.dto.idbook.IdBookCreateUpdateDto;
import by.sergo.book.app.libraryservice.domain.dto.idbook.IdBookResponseDto;
import by.sergo.book.app.libraryservice.domain.entity.IdBook;
import by.sergo.book.app.libraryservice.mapper.idbook.IdBookCreateMapper;
import by.sergo.book.app.libraryservice.mapper.idbook.IdBookResponseMapper;
import by.sergo.book.app.libraryservice.mapper.idbook.IdBookUpdateMapper;
import by.sergo.book.app.libraryservice.repository.IdBookRepository;
import by.sergo.book.app.libraryservice.service.exception.ExceptionMessageUtil;
import by.sergo.book.app.libraryservice.service.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
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
