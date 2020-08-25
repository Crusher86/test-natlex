package dev.krop.sections.repositories;

import dev.krop.sections.repositories.entities.GeologicalObjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface GeologicalObjectRepository extends JpaRepository<GeologicalObjectEntity, UUID> {

    GeologicalObjectEntity getByName(String name);
}
