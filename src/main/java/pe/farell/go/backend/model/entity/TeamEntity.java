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

@Entity
@Table(name = "T_TEAM", schema = "tesis")
@Getter
@Setter
public class TeamEntity extends AuditModel{
    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "T_TEAM_ID_SEQ", sequenceName = "t_team_id_seq", allocationSize = 1, schema = "tesis")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "T_TEAM_ID_SEQ" )
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    @Lob
    private String description;

    @Column(name = "status")
    private Integer status;
}
