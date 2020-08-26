package dev.krop.sections.controllers;

import dev.krop.sections.services.IEService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileUrlResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.net.MalformedURLException;



@RestController
public class IEController {

    private final IEService service;

    @Autowired
    public IEController(IEService service) {
        this.service = service;
    }

    @GetMapping("/export")
    public long exportData() {
        return service.exportData();
    }

    @GetMapping("/export/{id}")
    public String statusExport(@PathVariable ("id") long id) {
        return service.getStatus(id);
    }

    @GetMapping(path = "/export/{id}/file", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<Resource> getExportFile(@PathVariable ("id") long id) throws MalformedURLException {

        File file = service.getExportFile(id);
        Resource resource = new FileUrlResource(file.getPath());
        return ResponseEntity
                .ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header("Content-Disposition", "attachment; filename=\"" + file.getName() + "\"" )
                .body(resource);
    }

    @PostMapping("/import")
    public long importData(@RequestBody MultipartFile file) {
        return service.importData(file);
    }

    @GetMapping("/import/{id}")
    public String statusImport(@PathVariable ("id") long id) {
        return service.getStatus(id);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> exceptionHandler(RuntimeException exception) {
        return new ResponseEntity<>(exception.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
