package mo.spring.auditusingspringaop.services;

import java.util.List;
import java.util.Optional;

public interface IService<T> {
    Optional<T> findById(Long id);

    List<T> findAll();

    T save(T entity);

    T update(T entity);

    void deleteById(Long id);
}
