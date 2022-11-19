package pe.farell.go.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.farell.go.backend.model.entity.UserEntity;

@Repository
public interface UserRepositoryFree extends JpaRepository<UserEntity, Integer> {
}
