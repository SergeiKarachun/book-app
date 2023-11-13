package by.sergo.book.app.libraryservice.mapper.idbook;

import by.sergo.book.app.libraryservice.domain.dto.idbook.IdBookCreateUpdateDto;
import by.sergo.book.app.libraryservice.domain.entity.IdBook;
import by.sergo.book.app.libraryservice.domain.entity.RentalDate;
import by.sergo.book.app.libraryservice.mapper.CreateMapper;
import by.sergo.book.app.libraryservice.mapper.rentaldate.RentalDateResponseMapper;
import by.sergo.book.app.libraryservice.repository.RentalDateRepository;
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
