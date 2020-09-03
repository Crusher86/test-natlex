package dev.krop.sections.repositories.entities;

import dev.krop.sections.services.ie.Status;
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

    @Enumerated
    @Column(name = "job_status")
    Status jobStatus;

    @Column(name = "type")
    String type;

    @Column(name = "path")
    String path;

    public IEStatusEntity(Status jobStatus, String type, String path) {
        this.jobStatus = jobStatus;
        this.type = type;
        this.path = path;
    }
}
