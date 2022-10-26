package pe.farell.go.backend.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "T_SPRINT", schema = "tesis")
@Getter
@Setter
public class SprintEntity extends AuditModel {

    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "T_HU_SEQ_ID", sequenceName = "t_historyuser_id_seq", allocationSize = 1, schema = "tesis")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "T_HU_SEQ_ID" )
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "begin_date")
    private LocalDateTime beginDate;

    @Column(name = "end_date")
    private LocalDateTime endDate;

    @Column(name = "status")
    private Integer status;

}
