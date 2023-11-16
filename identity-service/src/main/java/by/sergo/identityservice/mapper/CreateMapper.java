package by.sergo.identityservice.mapper;

public interface CreateMapper<F, T> extends Mapper<F, T> {

    T mapToEntity(F requestDto);
}
