package pe.farell.go.backend.model.dto.response;

import lombok.Data;
import org.springframework.data.annotation.Immutable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Immutable
@Table(schema = "tesis")
public class ReportTaskView {
    private Integer sprint;
    private Integer value;
    @Id
    private String name;
}
