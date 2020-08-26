package dev.krop.sections.services;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;

public interface IEService {

    long exportData();

    long importData(MultipartFile file);

    String getStatus(long id);

    File getExportFile(long id);
}
