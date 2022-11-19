package pe.farell.go.backend.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pe.farell.go.backend.model.dto.request.PersonRequestDto;
import pe.farell.go.backend.model.dto.response.Content;
import pe.farell.go.backend.model.dto.response.Response;
import pe.farell.go.backend.model.entity.PersonEntity;
import pe.farell.go.backend.model.entity.UserEntity;
import pe.farell.go.backend.repository.PersonRepository;
import pe.farell.go.backend.repository.UserRepositoryFree;
import pe.farell.go.backend.security.enums.RolName;
import pe.farell.go.backend.security.model.dto.NewUser;
import pe.farell.go.backend.security.model.entity.Rol;
import pe.farell.go.backend.security.model.entity.User;
import pe.farell.go.backend.security.repository.UserRepository;
import pe.farell.go.backend.security.service.RolService;
import pe.farell.go.backend.service.Person;
import pe.farell.go.backend.util.ResponseUtil;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Slf4j
public class PersonService implements Person {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    RolService rolService;

    @Autowired
    UserRepositoryFree userRepositoryFree;

    @Override
    public Response<Content<PersonEntity>> getAllPersons() {
        List<PersonEntity> persons = personRepository.findAll();
        return ResponseUtil.validateList(persons, "La consulta de personas no retorno resultados");
    }

    @Override
    public Response<PersonEntity> save(PersonRequestDto personRequestDto) {

        NewUser newUser = new NewUser();
        mapper.map(personRequestDto, newUser);
        User user = new User(newUser.getUsername(), passwordEncoder.encode(newUser.getPassword()), "{}", 1);

        Set<Rol> roles = new HashSet<>();
        roles.add(rolService.getByRolName(RolName.ROLE_USER).get());
        if (newUser.getRoles().contains("tecnico")) {
            roles.add(rolService.getByRolName(RolName.ROLE_TECHNICAL).get());
        }

        if (newUser.getRoles().contains("supervisor")) {
            roles.add(rolService.getByRolName(RolName.ROLE_SUPERVISOR).get());
        }

        if (newUser.getRoles().contains("administrador")) {
            roles.add(rolService.getByRolName(RolName.ROLE_ADMIN).get());
        }

        if (newUser.getRoles().contains("master")) {
            roles.add(rolService.getByRolName(RolName.ROLE_MASTER).get());
        }
        user.setRoles(roles);
        user = userRepository.save(user);

        PersonEntity personEntity = new PersonEntity();
        mapper.map(personRequestDto, personEntity);

        Optional<UserEntity> userEntity = userRepositoryFree.findById(user.getId());

        personEntity.setUser(userEntity.get());

        return ResponseUtil.validateObj(personRepository.save(personEntity), null);
    }

}
