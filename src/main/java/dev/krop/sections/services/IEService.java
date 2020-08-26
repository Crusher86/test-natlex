package dev.krop.sections.services;

import java.io.IOException;

public interface IEService {

    void exportData(String path) throws IOException;
    void importData(String path) throws IOException;
}
