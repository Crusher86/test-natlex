package dev.krop.sections.configs;

import dev.krop.sections.repositories.entities.SectionEntity;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EntityScan(basePackageClasses = SectionEntity.class)
public class RepositoriesConfig {
}
