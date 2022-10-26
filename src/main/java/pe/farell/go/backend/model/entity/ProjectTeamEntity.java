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

@Entity
@Table(name = "T_PROJECTXTEAM", schema = "tesis")
@Getter
@Setter
public class ProjectTeamEntity {
    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "T_PROJECTXTEAM_ID_SEQ", sequenceName = "t_projxteam_id_seq", allocationSize = 1, schema = "tesis")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "T_PROJECTXTEAM_ID_SEQ" )
    private Integer id;

    @Column(name = "id_team")
    private Integer team;

    @Column(name = "id_project")
    private Integer project;
}
