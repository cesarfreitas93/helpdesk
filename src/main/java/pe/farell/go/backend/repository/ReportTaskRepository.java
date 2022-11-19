package pe.farell.go.backend.repository;

import pe.farell.go.backend.model.dto.response.ReportTaskView;

import java.util.List;

public interface ReportTaskRepository extends ReadOnlyRepository<ReportTaskView, Integer>{
    List<ReportTaskView> findAllBySprint(Integer sprintId);
}
