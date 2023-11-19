package by.sergo.identityservice.mapper;

public interface ResponseMapper<F, T> extends Mapper<F, T> {

    T mapToDto(F entity);
}
