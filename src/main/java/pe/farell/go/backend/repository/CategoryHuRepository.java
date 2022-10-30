package pe.farell.go.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.farell.go.backend.model.entity.CategoryEntity;

public interface CategoryHuRepository extends JpaRepository<CategoryEntity, Integer> {
}
