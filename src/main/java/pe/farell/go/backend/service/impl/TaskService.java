package pe.farell.go.backend.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.farell.go.backend.constant.EnumResponse;
import pe.farell.go.backend.exception.ValidationException;
import pe.farell.go.backend.model.dto.request.TaskRequestCreateDto;
import pe.farell.go.backend.model.dto.response.Response;
import pe.farell.go.backend.model.entity.TaskEntity;
import pe.farell.go.backend.repository.TaskRepository;
import pe.farell.go.backend.service.Task;
import pe.farell.go.backend.util.ResponseUtil;

import java.util.Optional;

@Service
public class TaskService implements Task {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public Response<TaskEntity> save(TaskRequestCreateDto request) {
        TaskEntity taskEntity = new TaskEntity();
        mapper.map(request, taskEntity);
        return ResponseUtil.validateObj(taskRepository.save(taskEntity), null);
    }

    @Override
    public Response<TaskEntity> update(Integer id, TaskRequestCreateDto request) {
        Optional<TaskEntity> taskEntity = taskRepository.findById(id);
        if (!taskEntity.isPresent()) {
            throw new ValidationException(EnumResponse.C003.getCode(), "La tarea que desea actualizar no existe.");
        }
        TaskEntity task = new TaskEntity();
        mapper.map(request, task);
        return ResponseUtil.validateObj(taskRepository.save(task), null);
    }

    @Override
    public Response<TaskEntity> getById(Integer id) {
        Optional<TaskEntity> taskEntityOptional = taskRepository.findById(id);
        if (!taskEntityOptional.isPresent()) {
            throw new ValidationException(EnumResponse.C003.getCode(), "La tarea que desea abrir no existe.");
        }
        return ResponseUtil.validateObj(taskEntityOptional.get(), null);
    }
}
