package pe.farell.go.backend.exception.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.farell.go.backend.exception.security.repository.UserRepository;
import pe.farell.go.backend.exception.security.model.entity.User;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Optional<User> getByUserName(String username) {
        return userRepository.findByUsername(username);
    }

    public boolean existByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    public void save(User user) {
        userRepository.save(user);
    }

}
