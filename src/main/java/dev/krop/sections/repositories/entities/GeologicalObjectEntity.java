package dev.krop.sections.repositories.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.Objects;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@Table(name = "geological_objects", uniqueConstraints = @UniqueConstraint(columnNames = "name"))
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GeologicalObjectEntity {

    @Id
    @GeneratedValue
    @Column(name = "id")
    UUID id;

    @Column(name = "name")
    String name;

    @Column(name = "code")
    String code;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "section_id")
    SectionEntity section;

    @Builder
    public GeologicalObjectEntity(String name, String code) {
        this.name = name;
        this.code = code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GeologicalObjectEntity that = (GeologicalObjectEntity) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(code, that.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, code);
    }
}
