package pe.farell.go.backend.model.entity;

import lombok.Data;
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
@Table(name = "T_PROJECT", schema = "tesis")
@Getter
@Setter
public class ProjectEntity extends AuditModel {
    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "T_PROJECT_ID_SEQ", sequenceName = "t_project_id_seq", allocationSize = 1, schema = "tesis")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "T_PROJECT_ID_SEQ" )
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    @Lob
    private String description;

    @Column(name = "status")
    private Integer status;

    @Column(name = "config")
    @Lob
    private String config;

    @Column(name = "files")
    @Lob
    private String files;
}
