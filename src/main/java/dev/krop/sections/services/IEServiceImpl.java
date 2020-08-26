package dev.krop.sections.services;

import dev.krop.sections.repositories.IERepository;
import dev.krop.sections.repositories.entities.IEStatusEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

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
    public long exportData() {
        IEStatusEntity entity = createJobId("export");
        String path = "./job" + entity.getJobId() + ".xls";
        entity.setPath(path);
        processService.exportData(path)
                .thenApplyAsync(done -> complete(entity, done));
        return entity.getJobId();
    }

    @Override
    public long importData(MultipartFile file) {
        IEStatusEntity entity = createJobId("import");
        processService.importData(file)
                .thenApplyAsync(done -> complete(entity, done));
        return entity.getJobId();
    }

    @Override
    public String getStatus(long id) {
        return repository.getByJobId(id).getJobStatus();
    }

    @Override
    public File getExportFile(long id) {
        IEStatusEntity entity = repository.getByJobId(id);
        Status status = Status.valueOf(entity.getJobStatus());
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
                new IEStatusEntity(Status.IN_PROGRESS.toString(),
                        type, null));
    }

    private Status complete(IEStatusEntity entity, Boolean done) {
        if (done) {
            entity.setJobStatus(Status.DONE.toString());
        } else {
            entity.setJobStatus(Status.ERROR.toString());
        }
        repository.saveAndFlush(entity);
        return Status.valueOf(entity.getJobStatus());
    }
}
