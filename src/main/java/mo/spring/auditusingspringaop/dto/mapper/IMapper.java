package mo.spring.auditusingspringaop.dto.mapper;

import java.util.List;
import java.util.Set;

public interface IMapper {
    <T> T map(Object source, Class<T> targetClass);

    <S, T> List<T> mapList(List<S> source, Class<T> targetClass);

    <S, T> Set<T> mapSet(Set<S> source, Class<T> targetClass);
}
