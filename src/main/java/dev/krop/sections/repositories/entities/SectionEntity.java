package dev.krop.sections.repositories.entities;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@Table(name = "sections")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SectionEntity {

    @Id
    @GeneratedValue
    @Column(name = "id")
    UUID id;

    @Column(name = "name")
    String name;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "section", cascade = CascadeType.ALL)
    List<GeologicalObjectEntity> geologicalObjectEntities;

    @Builder
    public SectionEntity(String name, List<GeologicalObjectEntity> geologicalObjectEntities) {
        this.name = name;
        this.geologicalObjectEntities = geologicalObjectEntities;
    }
}
