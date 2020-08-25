package dev.krop.sections.services;

import dev.krop.sections.models.GeologicalObject;
import dev.krop.sections.repositories.entities.GeologicalObjectEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MapperGeologicalObjectService implements MapperService<GeologicalObject, GeologicalObjectEntity> {

    @Override
    public GeologicalObjectEntity mapModelToEntity(GeologicalObject section) {
        return GeologicalObjectEntity.builder()
                .name(section.getName())
                .code(section.getCode())
                .build();
    }

    @Override
    public GeologicalObject mapEntityToModel(GeologicalObjectEntity entity) {
        return GeologicalObject.builder()
                .name(entity.getName())
                .code(entity.getCode())
                .build();
    }

    @Override
    public List<GeologicalObject> mapListEntityToListModel(List<GeologicalObjectEntity> entities) {
        List<GeologicalObject> geologicalObjects = new ArrayList<>();
        for (GeologicalObjectEntity entity : entities) {
            geologicalObjects.add(mapEntityToModel(entity));
        }
        return geologicalObjects;
    }

    @Override
    public List<GeologicalObjectEntity> mapListModelToListEntity(List<GeologicalObject> models) {
        List<GeologicalObjectEntity> entities = new ArrayList<>();
        for (GeologicalObject model : models) {
            entities.add(mapModelToEntity(model));
        }
        return entities;
    }

    @Override
    public GeologicalObjectEntity mapModelToEntityWithSavedEntity(GeologicalObjectEntity savedEntity, GeologicalObject model) {

        GeologicalObjectEntity geologicalObjectEntity = mapModelToEntity(model);
        geologicalObjectEntity.setId(savedEntity.getId());

        return geologicalObjectEntity;
    }
}
