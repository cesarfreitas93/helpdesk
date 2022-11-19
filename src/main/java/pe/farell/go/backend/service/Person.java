package pe.farell.go.backend.service;

import pe.farell.go.backend.model.dto.request.PersonRequestDto;
import pe.farell.go.backend.model.dto.response.Content;
import pe.farell.go.backend.model.dto.response.Response;
import pe.farell.go.backend.model.entity.PersonEntity;

public interface Person {

    Response<Content<PersonEntity>> getAllPersons();

    Response<PersonEntity> save(PersonRequestDto personRequestDto);

}
