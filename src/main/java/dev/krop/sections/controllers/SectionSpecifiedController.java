package dev.krop.sections.controllers;

import dev.krop.sections.models.Section;
import dev.krop.sections.services.SectionSpecifiedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("sections")
public class SectionSpecifiedController {

    private final SectionSpecifiedService specifiedService;

    @Autowired
    public SectionSpecifiedController(SectionSpecifiedService specifiedService) {
        this.specifiedService = specifiedService;
    }

    @GetMapping("/by-code")
    public List<Section> getByCode(@RequestParam(required = false) String code) {
        return specifiedService.getByGeologyCode(code);
    }
}
