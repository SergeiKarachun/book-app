package by.sergo.book.app.libraryservice.mapper.idbook;

import by.sergo.book.app.libraryservice.domain.dto.idbook.IdBookResponseDto;
import by.sergo.book.app.libraryservice.domain.entity.IdBook;
import by.sergo.book.app.libraryservice.mapper.ResponseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class IdBookResponseMapper implements ResponseMapper<IdBook, IdBookResponseDto> {

    @Override
    public IdBookResponseDto mapToDto(IdBook entity) {
        return new IdBookResponseDto(entity.getId(), entity.getBookId());
    }
}
