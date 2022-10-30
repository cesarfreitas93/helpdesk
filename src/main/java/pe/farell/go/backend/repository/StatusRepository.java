package pe.farell.go.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.farell.go.backend.model.entity.StatusEntity;

public interface StatusRepository extends JpaRepository<StatusEntity, Integer> {
}
