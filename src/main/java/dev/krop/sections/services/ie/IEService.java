package dev.krop.sections.services.ie;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

public interface IEService {

    UUID exportData();

    UUID importData(MultipartFile file);

    Status getStatus(UUID id);

    File getExportFile(UUID id);
}
