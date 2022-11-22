package pe.farell.go.backend.service;

import pe.farell.go.backend.model.dto.request.TaskRequestCreateDto;
import pe.farell.go.backend.model.dto.response.Content;
import pe.farell.go.backend.model.dto.response.Response;
import pe.farell.go.backend.model.dto.response.ReportTaskView;
import pe.farell.go.backend.model.entity.TaskEntity;

public interface Task {

    Response<TaskEntity> save(TaskRequestCreateDto request);

    Response<TaskEntity> update(Integer id, TaskRequestCreateDto request);

    Response<TaskEntity> getById(Integer id);
    Response<Content<ReportTaskView>> getReportTaskBySprint(Integer id);

}
