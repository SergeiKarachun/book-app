package by.sergo.book.app.libraryservice.mapper;

public interface CreateMapper<F, T> extends Mapper<F, T> {

    T mapToEntity(F requestDto);
}
