package mo.spring.auditusingspringaop.services;

import java.util.List;
import java.util.Optional;

public interface IService<T> {
    T findById(Long id);

    List<T> findAll();

    T save(T dto);

    T update(T dto);

    void deleteById(Long id);
}
