package pe.farell.go.backend.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pe.farell.go.backend.model.dto.response.Content;
import pe.farell.go.backend.model.dto.response.Response;
import pe.farell.go.backend.model.entity.PersonEntity;
import pe.farell.go.backend.model.entity.UserEntity;
import pe.farell.go.backend.repository.PersonRepository;

@ContextConfiguration(classes = {PersonService.class})
@ExtendWith(SpringExtension.class)
class PersonServiceTest {
    @MockBean
    private PersonRepository personRepository;

    @Autowired
    private PersonService personService;

    /**
     * Method under test: {@link PersonService#getAllPersons()}
     */
    @Test
    void testGetAllPersons() {
        when(personRepository.findAll()).thenReturn(new ArrayList<>());
        Response<Content<PersonEntity>> actualAllPersons = personService.getAllPersons();
        assertEquals(2, actualAllPersons.getCode().intValue());
        assertEquals("La consulta de personas no retorno resultados", actualAllPersons.getMsg());
        assertTrue(actualAllPersons.getData().getContent().isEmpty());
        verify(personRepository).findAll();
    }

    /**
     * Method under test: {@link PersonService#getAllPersons()}
     */
    @Test
    void testGetAllPersons2() {
        UserEntity userEntity = new UserEntity();
        userEntity.onPreUpdate();
        userEntity.setConfig("La consulta de personas no retorno resultados");
        userEntity.setCreateAt(null);
        userEntity.setCreateUser(1);
        userEntity.setId(1);
        userEntity.setPassword("iloveyou");
        userEntity.setPerson(new PersonEntity());
        userEntity.setStatus(1);
        userEntity.setUpdateAt(null);
        userEntity.setUpdateUser(1);
        userEntity.setUsername("janedoe");

        PersonEntity personEntity = new PersonEntity();
        personEntity.setDateOfBirth(LocalDateTime.of(1, 1, 1, 1, 1));
        personEntity.setDocnumber(10);
        personEntity.setDoctype(1);
        personEntity.setId(1);
        personEntity.setName("La consulta de personas no retorno resultados");
        personEntity.setUser(userEntity);

        UserEntity userEntity1 = new UserEntity();
        userEntity1.onPreUpdate();
        userEntity1.setConfig("La consulta de personas no retorno resultados");
        userEntity1.setCreateAt(LocalDateTime.of(1, 1, 1, 1, 1));
        userEntity1.setCreateUser(1);
        userEntity1.setId(1);
        userEntity1.setPassword("iloveyou");
        userEntity1.setPerson(personEntity);
        userEntity1.setStatus(1);
        userEntity1.setUpdateAt(LocalDateTime.of(1, 1, 1, 1, 1));
        userEntity1.setUpdateUser(1);
        userEntity1.setUsername("janedoe");

        PersonEntity personEntity1 = new PersonEntity();
        personEntity1.setDateOfBirth(LocalDateTime.of(1, 1, 1, 1, 1));
        personEntity1.setDocnumber(10);
        personEntity1.setDoctype(1);
        personEntity1.setId(1);
        personEntity1.setName("La consulta de personas no retorno resultados");
        personEntity1.setUser(userEntity1);

        ArrayList<PersonEntity> personEntityList = new ArrayList<>();
        personEntityList.add(personEntity1);
        when(personRepository.findAll()).thenReturn(personEntityList);
        Response<Content<PersonEntity>> actualAllPersons = personService.getAllPersons();
        assertEquals(1, actualAllPersons.getCode().intValue());
        assertEquals("SUCCESS", actualAllPersons.getMsg());
        assertEquals(1, actualAllPersons.getData().getContent().size());
        verify(personRepository).findAll();
    }
}

