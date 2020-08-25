package dev.krop.sections.controllers;

import dev.krop.sections.models.GeologicalObject;
import dev.krop.sections.services.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("geological")
public class GeologicalCrudController {

    private final CrudService<GeologicalObject> service;

    @Autowired
    public GeologicalCrudController(CrudService<GeologicalObject> service) {
        this.service = service;
    }

    @GetMapping("/")
    public List<GeologicalObject> getGeologicalObject() {
        return service.getAll();
    }

    @GetMapping("/{name}")
    public GeologicalObject getGeologicalObject(@PathVariable("name") String name) {
        return service.getByName(name);
    }

    @PostMapping()
    public GeologicalObject addGeologicalObject(@RequestBody GeologicalObject geologicalObject) {
        return service.add(geologicalObject);
    }

    @PutMapping()
    public void updateGeologicalObject(@RequestBody GeologicalObject geologicalObject) {
        service.update(geologicalObject);
    }

    @DeleteMapping("/{name}")
    public void deleteGeologicalObject(@PathVariable ("name") String name) {
        service.delete(name);
    }
}
