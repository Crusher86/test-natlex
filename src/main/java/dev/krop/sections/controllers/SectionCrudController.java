package dev.krop.sections.controllers;

import dev.krop.sections.models.Section;
import dev.krop.sections.services.crud.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("section")
public class SectionCrudController {

    private final CrudService<Section> sectionService;

    @Autowired
    public SectionCrudController(CrudService<Section> sectionService) {
        this.sectionService = sectionService;
    }

    @GetMapping("/")
    public List<Section> getSections() {
        return sectionService.getAll();
    }

    @GetMapping("/{name}")
    public Section getSection(@PathVariable ("name") String name) {
        return sectionService.getByName(name);
    }

    @PostMapping()
    public Section addSection(@RequestBody Section section) {
        return sectionService.add(section);
    }

    @PutMapping()
    public void updateSection(@RequestBody Section section) {
        sectionService.update(section);
    }

    @DeleteMapping("/{name}")
    public void deleteSection(@PathVariable ("name") String name) {
        sectionService.delete(name);
    }
}
