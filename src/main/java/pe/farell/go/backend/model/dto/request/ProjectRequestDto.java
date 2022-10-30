package pe.farell.go.backend.model.dto.request;

import lombok.Data;

@Data
public class ProjectRequestDto {
    private String name;
    private String description;
    private Integer status;
    private String config;
    private String files;
}
