package dev.krop.sections.services;

import dev.krop.sections.models.Section;
import dev.krop.sections.repositories.SectionRepository;
import dev.krop.sections.repositories.entities.SectionEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SectionSpecifiedServiceImpl implements SectionSpecifiedService {

    private final SectionRepository repository;
    private final MapperService<Section, SectionEntity> mapperService;

    @Autowired
    public SectionSpecifiedServiceImpl(
            SectionRepository repository,
            MapperService<Section, SectionEntity> mapperService
    ) {
        this.repository = repository;
        this.mapperService = mapperService;
    }

    @Override
    public List<Section> getByGeologyCode(String code) {
        List<SectionEntity> resultEntity = repository.findAllByGeologicalObjectEntitiesCode(code);
        return mapperService.mapListEntityToListModel(resultEntity);
    }
}
