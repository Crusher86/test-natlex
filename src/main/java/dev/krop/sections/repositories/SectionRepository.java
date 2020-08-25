package dev.krop.sections.repositories;

import dev.krop.sections.repositories.entities.SectionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SectionRepository extends JpaRepository<SectionEntity, UUID> {

    SectionEntity getByName(String name);
}
