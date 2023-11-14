package by.sergo.book.app.service;

import by.sergo.book.app.domain.dto.genre.GenreCreateUpdateRequestDto;
import by.sergo.book.app.domain.dto.genre.GenreResponseDto;
import by.sergo.book.app.domain.entity.Genre;
import by.sergo.book.app.mapper.genre.GenreCreateMapper;
import by.sergo.book.app.mapper.genre.GenreResponseMapper;
import by.sergo.book.app.mapper.genre.GenreUpdateMapper;
import by.sergo.book.app.repository.GenreRepository;
import by.sergo.book.app.service.exception.ExceptionMessageUtil;
import by.sergo.book.app.service.exception.BadRequestException;
import by.sergo.book.app.service.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GenreService {

    private final GenreRepository genreRepository;
    private final GenreCreateMapper genreCreateMapper;
    private final GenreUpdateMapper genreUpdateMapper;
    private final GenreResponseMapper genreResponseMapper;

    @Transactional
    public Optional<GenreResponseDto> create(GenreCreateUpdateRequestDto dto) {
        checkUniqueGenreName(dto.name());

        return Optional.of(genreCreateMapper.mapToEntity(dto))
                .map(genreRepository::saveAndFlush)
                .map(genreResponseMapper::mapToDto);
    }

    @Transactional
    public Optional<GenreResponseDto> update(Long id, GenreCreateUpdateRequestDto dto) {
        var genre = getByIdOrElseThrow(id);

        if (!genre.getName().equals(dto.name())) {
            checkUniqueGenreName(dto.name());
        }

        return Optional.of(genreUpdateMapper.mapToEntity(dto, getByIdOrElseThrow(id)))
                .map(genreRepository::saveAndFlush)
                .map(genreResponseMapper::mapToDto);
    }

    public Optional<GenreResponseDto> getById(Long id) {
        return Optional.of(getByIdOrElseThrow(id))
                .map(genreResponseMapper::mapToDto);
    }

    public List<GenreResponseDto> getAll() {
        return genreRepository.findAll()
                .stream()
                .map(genreResponseMapper::mapToDto)
                .toList();
    }

    @Transactional
    public boolean deleteById(Long id) {
        if (genreRepository.existsById(id)) {
            genreRepository.deleteById(id);
            return true;
        }
        return false;
    }

    private Genre getByIdOrElseThrow(Long id) {
        return genreRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ExceptionMessageUtil.getNotFoundMessage("Genre", "id", id)));
    }

    private void checkUniqueGenreName(String genreName) {
        if (genreRepository.existsByNameIgnoreCase(genreName)) {
            throw new BadRequestException(
                    ExceptionMessageUtil.getAlreadyExistsMessage("Genre", "name", genreName));
        }
    }
}
