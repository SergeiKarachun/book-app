package by.sergo.book.app.mapper.idbook;

import by.sergo.book.app.domain.dto.idbook.IdBookCreateUpdateDto;
import by.sergo.book.app.domain.entity.IdBook;
import by.sergo.book.app.mapper.CreateMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class IdBookCreateMapper implements CreateMapper<IdBookCreateUpdateDto, IdBook> {

    @Override
    public IdBook mapToEntity(IdBookCreateUpdateDto requestDto) {
        return IdBook.builder()
                .bookId(requestDto.bookId())
                .build();
    }
}
