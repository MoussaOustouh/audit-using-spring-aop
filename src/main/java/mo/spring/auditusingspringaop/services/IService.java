package mo.spring.auditusingspringaop.services;

import mo.spring.auditusingspringaop.entities.Member;

import java.util.List;
import java.util.Optional;

public interface IService<T> {
    Optional<T> findOne(Long id);

    List<T> findAll();

    T save(T entity);

    T update(T entity);

    void delete(Long id);
}
