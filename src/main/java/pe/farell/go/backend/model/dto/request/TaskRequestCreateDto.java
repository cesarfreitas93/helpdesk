package pe.farell.go.backend.model.dto.request;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TaskRequestCreateDto {
    private Integer id;
    private String name;
    private String description;
    private Integer status;
    private Integer assignedto;
    private String files;
    private Integer ticket;
    private LocalDateTime completeAt;
    private Integer completeUser;
}
