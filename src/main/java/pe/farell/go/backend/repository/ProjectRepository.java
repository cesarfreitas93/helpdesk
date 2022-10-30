package pe.farell.go.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.farell.go.backend.model.entity.ProjectEntity;

public interface ProjectRepository extends JpaRepository<ProjectEntity, Integer> {
}
