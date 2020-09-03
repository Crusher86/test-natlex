package dev.krop.sections.services.mapping;

import java.util.List;

public interface MapperService<T, E> {

    E mapModelToEntity(T section);

    T mapEntityToModel(E entity);

    List<T> mapListEntityToListModel(List<E> entities);

    List<E> mapListModelToListEntity(List<T> models);

    E mapModelToEntityWithSavedEntity(E savedEntity, T model);
}
