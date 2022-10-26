package pe.farell.go.backend.exception.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.farell.go.backend.exception.security.repository.RolRepository;
import pe.farell.go.backend.exception.security.enums.RolName;
import pe.farell.go.backend.exception.security.model.entity.Rol;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class RolService {

    @Autowired
    RolRepository rolRepository;

    public Optional<Rol> getByRolName(RolName rolName) {
        return rolRepository.findByRolName(rolName);
    }
}
