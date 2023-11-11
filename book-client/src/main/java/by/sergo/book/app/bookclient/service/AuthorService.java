package by.sergo.book.app.bookclient.service;

import by.sergo.book.app.bookclient.domain.dto.author.AuthorCreateUpdateRequestDto;
import by.sergo.book.app.bookclient.domain.dto.author.AuthorResponseDto;
import by.sergo.book.app.bookclient.domain.entity.Author;
import by.sergo.book.app.bookclient.mapper.author.AuthorCreateMapper;
import by.sergo.book.app.bookclient.mapper.author.AuthorResponseMapper;
import by.sergo.book.app.bookclient.mapper.author.AuthorUpdateMapper;
import by.sergo.book.app.bookclient.repository.AuthorRepository;
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
public class AuthorService {

    private final AuthorRepository authorRepository;
    private final AuthorCreateMapper authorCreateMapper;
    private final AuthorResponseMapper authorResponseMapper;
    private final AuthorUpdateMapper authorUpdateMapper;

    @Transactional
    public Optional<AuthorResponseDto> create(AuthorCreateUpdateRequestDto dto) {
        return Optional.of(authorCreateMapper.mapToEntity(dto))
                .map(authorRepository::saveAndFlush)
                .map(authorResponseMapper::mapToDto);
    }

    @Transactional
    public Optional<AuthorResponseDto> update(Long id, AuthorCreateUpdateRequestDto dto) {
        var author = getByIdOrElseThrow(id);

        return Optional.of(authorUpdateMapper.mapToEntity(dto, author))
                .map(authorRepository::saveAndFlush)
                .map(authorResponseMapper::mapToDto);
    }

    public Optional<AuthorResponseDto> getById(Long id) {
        return Optional.of(getByIdOrElseThrow(id))
                .map(authorResponseMapper::mapToDto);
    }

    public List<AuthorResponseDto> getAll() {
        return authorRepository.findAll()
                .stream()
                .map(authorResponseMapper::mapToDto)
                .toList();
    }

    @Transactional
    public boolean deleteById(Long id) {
        if (authorRepository.existsById(id)) {
            authorRepository.deleteById(id);
            return true;
        }
        return false;
    }

    private Author getByIdOrElseThrow(Long id) {
        return authorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(
                        ExceptionMessageUtil.getNotFoundMessage("Author", "id", id)
                ));
    }
}
