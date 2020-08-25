package dev.krop.sections.services;

import dev.krop.sections.models.Section;

import java.util.List;

public interface SectionSpecifiedService {

    List<Section> getByGeologyCode(String code);
}
