package dev.krop.sections.services;

import org.springframework.web.multipart.MultipartFile;

import java.util.concurrent.CompletableFuture;

public interface IEProcessService {

    CompletableFuture<Boolean> exportData(String path);
    CompletableFuture<Boolean> importData(MultipartFile file);
}
