package dev.krop.sections.services;

import dev.krop.sections.models.GeologicalObject;
import dev.krop.sections.models.Section;
import dev.krop.sections.repositories.entities.GeologicalObjectEntity;
import dev.krop.sections.repositories.entities.SectionEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MapperSectionService implements MapperService<Section, SectionEntity> {

    private final MapperService<GeologicalObject, GeologicalObjectEntity> mapperService;

    @Autowired
    public MapperSectionService(MapperService<GeologicalObject, GeologicalObjectEntity> mapperService) {
        this.mapperService = mapperService;
    }

    @Override
    public SectionEntity mapModelToEntity(Section section) {

        List<GeologicalObjectEntity> geologicalObjectEntities
                = mapperService.mapListModelToListEntity(section.getGeologicalObjects());

        SectionEntity sectionEntity = SectionEntity.builder()
                .name(section.getName())
                .geologicalObjectEntities(geologicalObjectEntities)
                .build();

        for (GeologicalObjectEntity geologicalObjectEntity : geologicalObjectEntities) {
            geologicalObjectEntity.setSection(sectionEntity);
        }

        return sectionEntity;
    }

    @Override
    public Section mapEntityToModel(SectionEntity entity) {
        return Section.builder()
                .name(entity.getName())
                .geologicalObjects(mapperService.mapListEntityToListModel(entity.getGeologicalObjectEntities()))
                .build();
    }

    @Override
    public List<Section> mapListEntityToListModel(List<SectionEntity> entities) {
        List<Section> sections = new ArrayList<>();
        for (SectionEntity entity : entities) {
            sections.add(mapEntityToModel(entity));
        }
        return sections;
    }

    @Override
    public List<SectionEntity> mapListModelToListEntity(List<Section> models) {
        List<SectionEntity> entities = new ArrayList<>();
        for (Section model : models) {
            entities.add(mapModelToEntity(model));
        }
        return entities;
    }

    @Override
    public SectionEntity mapModelToEntityWithSavedEntity(SectionEntity savedEntity, Section model) {

        SectionEntity resultEntity = mapModelToEntity(model);
        resultEntity.setId(savedEntity.getId());

        for (GeologicalObjectEntity geologicalObject : resultEntity.getGeologicalObjectEntities()) {
            int index = savedEntity.getGeologicalObjectEntities().indexOf(geologicalObject);
            if (index != -1) {
                geologicalObject.setId(savedEntity.getGeologicalObjectEntities().get(index).getId());
            }
        }

        return resultEntity;
    }
}
