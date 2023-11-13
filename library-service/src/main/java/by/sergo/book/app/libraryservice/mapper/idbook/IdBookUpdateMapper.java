package by.sergo.book.app.libraryservice.mapper.idbook;

import by.sergo.book.app.libraryservice.domain.dto.idbook.IdBookCreateUpdateDto;
import by.sergo.book.app.libraryservice.domain.entity.IdBook;
import by.sergo.book.app.libraryservice.mapper.UpdateMapper;
import org.springframework.stereotype.Component;

@Component
public class IdBookUpdateMapper implements UpdateMapper<IdBookCreateUpdateDto, IdBook> {
    @Override
    public IdBook mapToEntity(IdBookCreateUpdateDto dto, IdBook entity) {
        merge(dto, entity);
        return entity;
    }

    @Override
    public void merge(IdBookCreateUpdateDto dto, IdBook entity) {
        entity.setBookId(dto.bookId());
    }
}
