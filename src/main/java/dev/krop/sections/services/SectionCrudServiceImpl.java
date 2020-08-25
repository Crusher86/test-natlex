package dev.krop.sections.services;

import dev.krop.sections.models.Section;
import dev.krop.sections.repositories.SectionRepository;
import dev.krop.sections.repositories.entities.SectionEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SectionCrudServiceImpl implements SectionCrudService {

    private final SectionRepository repository;
    private final MapperService<Section, SectionEntity> mapperService;

    @Autowired
    public SectionCrudServiceImpl(
            SectionRepository repository,
            MapperService<Section, SectionEntity> mapperService
    ) {
        this.repository = repository;
        this.mapperService = mapperService;
    }

    @Override
    public List<Section> getAllSection() {
        List<SectionEntity> sections = repository.findAll();
        return mapperService.mapListEntityToListModel(sections);
    }

    @Override
    public Section getSectionByName(String name) {
        SectionEntity entity = repository.getByName(name);
        return mapperService.mapEntityToModel(entity);
    }

    @Override
    public Section addSection(Section section) {
        SectionEntity entity = repository.saveAndFlush(mapperService.mapModelToEntity(section));
        return mapperService.mapEntityToModel(entity);
    }

    @Override
    public void updateSection(Section section) {
        SectionEntity savedEntity = repository.getByName(section.getName());
        SectionEntity newEntity = mapperService.mapModelToEntityWithSavedEntity(savedEntity, section);
        newEntity.setId(savedEntity.getId());
        repository.saveAndFlush(newEntity);
    }

    @Override
    public void deleteSection(String name) {
        UUID sectionId = repository.getByName(name).getId();
        repository.deleteById(sectionId);
    }
}
