package dev.krop.sections.controllers;

import dev.krop.sections.services.IEService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class IEController {

    private final IEService service;

    @Autowired
    public IEController(IEService service) {
        this.service = service;
    }

    @GetMapping("/export")
    public void exportData(@RequestBody String path) throws IOException {
        service.exportData(path);
    }

    @GetMapping("/import")
    public void importData(@RequestBody String path) throws IOException {
        service.importData(path);
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity<IOException> exceptionHandler(IOException exception) {
        return new ResponseEntity<>(exception, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
