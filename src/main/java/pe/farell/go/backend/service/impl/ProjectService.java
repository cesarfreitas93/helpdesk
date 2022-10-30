package pe.farell.go.backend.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.farell.go.backend.constant.EnumResponse;
import pe.farell.go.backend.exception.ValidationException;
import pe.farell.go.backend.model.dto.request.ProjectRequestDto;
import pe.farell.go.backend.model.dto.request.TaskRequestCreateDto;
import pe.farell.go.backend.model.dto.response.Content;
import pe.farell.go.backend.model.dto.response.Response;
import pe.farell.go.backend.model.entity.ProjectEntity;
import pe.farell.go.backend.repository.ProjectRepository;
import pe.farell.go.backend.service.Project;
import pe.farell.go.backend.util.ResponseUtil;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectService implements Project {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public Response<ProjectEntity> save(ProjectRequestDto request) {
        ProjectEntity projectEntity = new ProjectEntity();
        mapper.map(request, projectEntity);
        return ResponseUtil.validateObj(projectRepository.save(projectEntity), null);
    }

    @Override
    public Response<ProjectEntity> update(Integer id, ProjectRequestDto request) {
        Optional<ProjectEntity> optionalProjectEntity = projectRepository.findById(id);
        if (!optionalProjectEntity.isPresent()) {
            throw new ValidationException(EnumResponse.C003.getCode(), "El proyecto que desea actualizar no existe.");
        }
        ProjectEntity projectEntity = new ProjectEntity();
        mapper.map(request, projectEntity);
        return ResponseUtil.validateObj(projectRepository.save(projectEntity), null);
    }

    @Override
    public Response<ProjectEntity> getById(Integer id) {
        Optional<ProjectEntity> taskEntityOptional = projectRepository.findById(id);
        if (!taskEntityOptional.isPresent()) {
            throw new ValidationException(EnumResponse.C003.getCode(), "El proyecto que desea abrir no existe.");
        }
        return ResponseUtil.validateObj(taskEntityOptional.get(), null);
    }

    @Override
    public Response<Content<ProjectEntity>> getAll() {
        List<ProjectEntity> persons = projectRepository.findAll();
        return ResponseUtil.validateList(persons, "La consulta de projectos no retorno resultados");
    }
}
