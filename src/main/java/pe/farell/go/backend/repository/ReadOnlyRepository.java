package pe.farell.go.backend.repository;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

@NoRepositoryBean
public interface ReadOnlyRepository <T, ID> extends Repository<T, ID> {
}
