package pe.farell.go.backend.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
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
    @Lob
    private String description;

    @Column(name = "status")
    private Integer status;

    @Column(name = "assignedto")
    private Integer assignedto;

    @Column(name = "files")
    @Lob
    private String files;

    @Column(name = "ticket")
    private Integer ticket;

    @Column(name = "complete_at")
    private LocalDateTime completeAt;

    @Column(name = "complete_user")
    private Integer completeUser;

}
