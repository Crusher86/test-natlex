package dev.krop.sections.services.crud;

import dev.krop.sections.models.GeologicalObject;
import dev.krop.sections.repositories.GeologicalObjectRepository;
import dev.krop.sections.repositories.entities.GeologicalObjectEntity;
import dev.krop.sections.services.mapping.MapperGeologicalObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class GeologicalCrudService implements CrudService<GeologicalObject> {

    private final GeologicalObjectRepository repository;
    private final MapperGeologicalObjectService mapperService;

    @Autowired
    public GeologicalCrudService(
            GeologicalObjectRepository repository,
            MapperGeologicalObjectService mapperService
    ) {
        this.repository = repository;
        this.mapperService = mapperService;
    }

    @Override
    public List<GeologicalObject> getAll() {
        List<GeologicalObjectEntity> geologicalObjectEntities = repository.findAll();
        return mapperService.mapListEntityToListModel(geologicalObjectEntities);
    }

    @Override
    public GeologicalObject getByName(String name) {
        GeologicalObjectEntity entity = repository.getByName(name);
        return mapperService.mapEntityToModel(entity);
    }

    @Override
    public GeologicalObject add(GeologicalObject geologicalObject) {
        GeologicalObjectEntity geologicalObjectEntity =
                repository.saveAndFlush(mapperService.mapModelToEntity(geologicalObject));
        return mapperService.mapEntityToModel(geologicalObjectEntity);
    }

    @Override
    public void update(GeologicalObject geologicalObject) {
        GeologicalObjectEntity savedEntity = repository.getByName(geologicalObject.getName());
        GeologicalObjectEntity resultEntity = mapperService.mapModelToEntityWithSavedEntity(savedEntity, geologicalObject);
        repository.saveAndFlush(resultEntity);
    }

    @Override
    public void delete(String name) {
        UUID id = repository.getByName(name).getId();
        repository.deleteById(id);
    }
}
