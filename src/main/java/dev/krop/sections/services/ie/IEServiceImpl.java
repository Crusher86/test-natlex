package dev.krop.sections.services.ie;

import dev.krop.sections.repositories.IERepository;
import dev.krop.sections.repositories.entities.IEStatusEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

@Service
public class IEServiceImpl implements IEService {

    private final IEProcessService processService;
    private final IERepository repository;

    @Autowired
    public IEServiceImpl(
            IEProcessService processService,
            IERepository repository
    ) {
        this.processService = processService;
        this.repository = repository;
    }

    @Override
    public UUID exportData() {
        IEStatusEntity entity = createJobId("export");
        String path = "./job" + entity.getId() + ".xls";
        entity.setPath(path);
        processService.exportData(path)
                .thenApplyAsync(done -> complete(entity, done));
        return entity.getId();
    }

    @Override
    public UUID importData(MultipartFile file) {
        IEStatusEntity entity = createJobId("import");
        processService.importData(file)
                .thenApplyAsync(done -> complete(entity, done));
        return entity.getId();
    }

    @Override
    public Status getStatus(UUID id) {
        return repository.getOne(id).getJobStatus();
    }

    @Override
    public File getExportFile(UUID id) {
        IEStatusEntity entity = repository.getOne(id);
        Status status = entity.getJobStatus();
        if (status == Status.DONE) {
            return new File(entity.getPath());
        } else if (status == Status.IN_PROGRESS) {
            throw new RuntimeException("Экспорт данных находится в процессе выполнения");
        } else {
            throw new RuntimeException("Экспорт данных завершился с ошибкой");
        }
    }

    private IEStatusEntity createJobId(String type) {
        return repository.saveAndFlush(
                new IEStatusEntity(Status.IN_PROGRESS,
                        type, null));
    }

    private Status complete(IEStatusEntity entity, Boolean done) {
        if (done) {
            entity.setJobStatus(Status.DONE);
        } else {
            entity.setJobStatus(Status.ERROR);
        }
        repository.saveAndFlush(entity);
        return entity.getJobStatus();
    }
}
