package pe.farell.go.backend.model.dto.request;

import lombok.Data;

@Data
public class TicketRequestCreateDto {

    private String name;
    private String description;
    private Integer assignedTo;
    private Integer category;
    private Integer weight;
    private Integer status;
    private Integer sprint;
    private Integer project;

}
