package dev.krop.sections.services;

import dev.krop.sections.models.GeologicalObject;
import dev.krop.sections.models.Section;
import dev.krop.sections.repositories.SectionRepository;
import dev.krop.sections.repositories.entities.GeologicalObjectEntity;
import dev.krop.sections.repositories.entities.SectionEntity;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class IEServiceImpl implements IEService {

    private final SectionRepository repository;
    private final MapperService<Section, SectionEntity> mapperService;

    @Autowired
    public IEServiceImpl(
            SectionRepository repository,
            MapperService<Section, SectionEntity> mapperService
    ) {
        this.repository = repository;
        this.mapperService = mapperService;
    }

    @Override
    public void exportData(String path) throws IOException {

        List<SectionEntity> sections = repository.findAll();
        int max = sections.stream()
                .map(section -> section.getGeologicalObjectEntities().size())
                .max(Integer::compare)
                .orElse(0);

        Workbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet("Data");

        Row row = sheet.createRow(0);

        Cell cell = row.createCell(0, CellType.STRING);
        cell.setCellValue("Section name");
        int numberCell = 1;

        for (int i = 1; i <= max; i++) {
             cell = row.createCell(numberCell, CellType.STRING);
             numberCell++;
             cell.setCellValue("Class " + i + " name");
             cell = row.createCell(numberCell, CellType.STRING);
             numberCell++;
             cell.setCellValue("Class " + i + " code");
        }

        int rowNum = 1;

        for (SectionEntity section : sections) {
            row = sheet.createRow(rowNum);
            cell = row.createCell(0, CellType.STRING);
            cell.setCellValue(section.getName());
            
            
            for (GeologicalObjectEntity geologicalObject : section.getGeologicalObjectEntities()) {
                numberCell = Integer.parseInt(geologicalObject.getCode().substring(3));
                if (numberCell > 1) {
                    numberCell = numberCell * 2 - 1;
                }
                cell = row.createCell(numberCell, CellType.STRING);
                numberCell++;
                cell.setCellValue(geologicalObject.getName());
                cell = row.createCell(numberCell, CellType.STRING);
                cell.setCellValue(geologicalObject.getCode());
            }
            rowNum++;
        }

        workbook.write(new FileOutputStream(path));
        workbook.close();
    }

    @Override
    public void importData(String path) throws IOException {
        List<Section> sections = new ArrayList<>();
        List<GeologicalObject> geologicalObjects = new ArrayList<>();
        Section section = new Section();
        GeologicalObject object = new GeologicalObject();

        HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(path));
        HSSFSheet sheet = workbook.getSheet("Data");

        Iterator<Row> rowIterator = sheet.iterator();

        if (rowIterator.hasNext())
            rowIterator.next();

        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();

            Iterator<Cell> cellIterator = row.cellIterator();

            if (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                section.setName(cell.getStringCellValue());
            }

            int numberCell = 0;

            while (cellIterator.hasNext()) {
                Cell innerCell = cellIterator.next();
                if (numberCell%2 == 0) {
                    object.setName(innerCell.getStringCellValue());
                } else {
                    object.setCode(innerCell.getStringCellValue());
                    geologicalObjects.add(object);
                    object = new GeologicalObject();
                }
                numberCell++;
            }
            section.setGeologicalObjects(geologicalObjects);
            sections.add(section);
            section = new Section();
            geologicalObjects = new ArrayList<>();
        }

        for (Section sect : sections) {
            repository.saveAndFlush(mapperService.mapModelToEntity(sect));
        }
    }
}
