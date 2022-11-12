package pe.farell.go.backend.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "T_TASK", schema = "tesis")
@Getter
@Setter
public class TaskEntity extends AuditModel{

    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "T_TASK_ID_SEQ", sequenceName = "t_task_id_seq", allocationSize = 1, schema = "tesis")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "T_TASK_ID_SEQ" )
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "status")
    private Integer status;

    @Column(name = "assignedto")
    private Integer assignedto;

    @Column(name = "files")
    private String files;

    @Column(name = "complete_at")
    private LocalDateTime completeAt;

    @Column(name = "complete_user")
    private Integer completeUser;

    @Column(name = "id_ticket")
    private Integer ticket;
}
