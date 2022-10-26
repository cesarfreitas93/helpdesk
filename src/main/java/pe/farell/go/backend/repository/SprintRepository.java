package pe.farell.go.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.farell.go.backend.model.entity.SprintEntity;

@Repository
public interface SprintRepository extends JpaRepository<SprintEntity, Integer> {
    SprintEntity findByName(String nameSprint);
}
