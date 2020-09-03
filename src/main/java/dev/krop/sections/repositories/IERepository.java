package dev.krop.sections.repositories;

import dev.krop.sections.repositories.entities.IEStatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IERepository extends JpaRepository<IEStatusEntity, UUID> {
}
