package pe.farell.go.backend.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.farell.go.backend.constant.EnumResponse;
import pe.farell.go.backend.exception.ValidationException;
import pe.farell.go.backend.model.dto.request.TaskRequestCreateDto;
import pe.farell.go.backend.model.dto.response.Content;
import pe.farell.go.backend.model.dto.response.Response;
import pe.farell.go.backend.model.dto.response.ReportTaskView;
import pe.farell.go.backend.model.entity.TaskEntity;
import pe.farell.go.backend.repository.ReportTaskRepository;
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

    @Autowired
    private ReportTaskRepository reportTaskRepository;

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

    @Override
    public Response<Content<ReportTaskView>> getReportTaskBySprint(Integer id) {
        Response<Content<ReportTaskView>> result = new Response<>();
        Content<ReportTaskView> tuplaIntegerStringContent = new Content<>();
        tuplaIntegerStringContent.setContent(reportTaskRepository.findAllBySprint(id));
        result.setData(tuplaIntegerStringContent);
        result.setCode(EnumResponse.C001.getCode());
        result.setMsg(EnumResponse.C001.getMsg());
        return result;
    }
}
