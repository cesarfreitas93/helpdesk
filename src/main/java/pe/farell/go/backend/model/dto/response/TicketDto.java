package pe.farell.go.backend.model.dto.response;

import lombok.Data;
import pe.farell.go.backend.model.entity.TaskEntity;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class TicketDto {
    private Integer id;
    private String name;
    private String description;
    private Integer category;
    private Integer weight;
    private Integer status;
    private LocalDateTime completeAt;
    private Integer completeUser;
    private Integer assignedTo;
    private Integer sprint;
    private Integer project;
    private LocalDateTime createAt;
    private List<TaskEntity> news;
    private List<TaskEntity> progress;
    private List<TaskEntity> complete;
}
