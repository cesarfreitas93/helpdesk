package pe.farell.go.backend.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "T_TICKETIT", schema = "tesis")
@Getter
@Setter
public class TicketEntity extends AuditModel {
    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "T_SPRINT_ID_SEQ", sequenceName = "t_sprint_id_seq1", allocationSize = 1, schema = "tesis")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "T_SPRINT_ID_SEQ" )
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
     private String description;

    @Column(name = "category")
    private Integer category;

    @Column(name = "weight")
    private Integer weignt;

    @Column(name = "status")
    private Integer status;

    @Column(name = "complete_at")
    private LocalDateTime completeAt;

    @Column(name = "complete_user")
    private Integer completeUser;

    @Column(name = "assignedto")
    private Integer assignedTo;

    @Column(name = "id_sprint")
    private Integer sprint;

    @Column(name = "id_project")
    private Integer project;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "id_ticket")
    private List<TaskEntity> tasks = new ArrayList<>();
}
