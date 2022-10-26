package pe.farell.go.backend.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.farell.go.backend.model.dto.response.Content;
import pe.farell.go.backend.model.dto.response.Response;
import pe.farell.go.backend.model.entity.PersonEntity;
import pe.farell.go.backend.repository.PersonRepository;
import pe.farell.go.backend.service.Person;
import pe.farell.go.backend.util.ResponseUtil;

import java.util.List;

@Service
@Slf4j
public class PersonService implements Person {

    @Autowired
    private PersonRepository personRepository;

    @Override
    public Response<Content<PersonEntity>> getAllPersons() {
        List<PersonEntity> persons = personRepository.findAll();
        return ResponseUtil.validateList(persons, "La consulta de personas no retorno resultados");
    }
}
