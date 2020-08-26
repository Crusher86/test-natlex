package dev.krop.sections.repositories.entities;

import dev.krop.sections.utils.Generator;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@Table(name = "status")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class IEStatusEntity {

    @Id
    @GeneratedValue
    @Column(name = "id")
    UUID id;

    @Column(name = "job_id")
    Long jobId;

    @Column(name = "job_status")
    String jobStatus;

    @Column(name = "type")
    String type;

    @Column(name = "path")
    String path;

    public IEStatusEntity(String jobStatus, String type, String path) {
        this.jobStatus = jobStatus;
        this.type = type;
        this.path = path;
        this.jobId = Generator.generateJobId();
    }
}
