package dev.krop.sections.services;

import dev.krop.sections.models.Section;

import java.util.List;

public interface SectionCrudService {

    List<Section> getAllSection();
    Section getSectionByName(String name);
    Section addSection(Section section);
    void updateSection(Section section);
    void deleteSection(String name);
}
