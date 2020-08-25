package dev.krop.sections.controllers;

import dev.krop.sections.models.Section;
import dev.krop.sections.services.SectionCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("section")
public class SectionCrudController {

    private final SectionCrudService sectionService;

    @Autowired
    public SectionCrudController(SectionCrudService sectionService) {
        this.sectionService = sectionService;
    }

    @GetMapping("/")
    public List<Section> getSections() {
        return sectionService.getAllSection();
    }

    @GetMapping("/{name}")
    public Section getSection(@PathVariable ("name") String name) {
        return sectionService.getSectionByName(name);
    }

    @PostMapping()
    public Section addSection(@RequestBody Section section) {
        return sectionService.addSection(section);
    }

    @PutMapping()
    public void updateSection(@RequestBody Section section) {
        sectionService.updateSection(section);
    }

    @DeleteMapping("/{name}")
    public void deleteSection(@PathVariable ("name") String name) {
        sectionService.deleteSection(name);
    }
}
