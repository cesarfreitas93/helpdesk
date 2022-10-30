package pe.farell.go.backend.service;

import pe.farell.go.backend.model.dto.request.ProjectRequestDto;
import pe.farell.go.backend.model.dto.response.Content;
import pe.farell.go.backend.model.dto.response.Response;
import pe.farell.go.backend.model.entity.ProjectEntity;

public interface Project {

    Response<ProjectEntity> save(ProjectRequestDto request);

    Response<ProjectEntity> update(Integer id, ProjectRequestDto request);

    Response<ProjectEntity> getById(Integer id);

    Response<Content<ProjectEntity>> getAll();
}

