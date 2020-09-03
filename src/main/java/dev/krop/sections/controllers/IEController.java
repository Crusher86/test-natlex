package dev.krop.sections.controllers;

import dev.krop.sections.services.ie.IEService;
import dev.krop.sections.services.ie.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileUrlResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.io.File;
import java.net.MalformedURLException;
import java.util.UUID;


@RestController
public class IEController {

    private final IEService service;

    @Autowired
    public IEController(IEService service) {
        this.service = service;
    }

    @GetMapping("/export")
    public UUID exportData() {
        return service.exportData();
    }

    @GetMapping("/export/{id}")
    public Status statusExport(@PathVariable ("id") UUID id) {
        return service.getStatus(id);
    }

    @GetMapping(path = "/export/{id}/file", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<Resource> getExportFile(@PathVariable ("id") UUID id) throws MalformedURLException {

        File file = service.getExportFile(id);
        Resource resource = new FileUrlResource(file.getPath());
        return ResponseEntity
                .ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header("Content-Disposition", "attachment; filename=\"" + file.getName() + "\"" )
                .body(resource);
    }

    @PostMapping("/import")
    public UUID importData(@RequestBody MultipartFile file) {
        return service.importData(file);
    }

    @GetMapping("/import/{id}")
    public Status statusImport(@PathVariable ("id") UUID id) {
        return service.getStatus(id);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> exceptionHandler(RuntimeException exception) {
        return new ResponseEntity<>(exception.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> exceptionHandler(EntityNotFoundException exception) {
        return new ResponseEntity<>(exception.getLocalizedMessage(), HttpStatus.NOT_FOUND);
    }
}
