package pe.farell.go.backend.service;

import pe.farell.go.backend.model.dto.request.SprintRequestCreateDto;
import pe.farell.go.backend.model.dto.response.Content;
import pe.farell.go.backend.model.dto.response.Response;
import pe.farell.go.backend.model.entity.SprintEntity;

public interface Sprint {

    Response<SprintEntity> save(SprintRequestCreateDto request);

    Response<Content<SprintEntity>> getAll();
    Response<Content<SprintEntity>> getLast();
}
