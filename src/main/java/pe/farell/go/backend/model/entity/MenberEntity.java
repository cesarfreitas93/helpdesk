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
@Table(name = "T_MENBER", schema = "tesis")
@Getter
@Setter
public class MenberEntity extends AuditModel{

    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "T_MENBER_SEQUENCE_ID", sequenceName = "t_menber_id_seq", allocationSize = 1, schema = "tesis")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "T_MENBER_SEQUENCE_ID" )
    private Integer id;

    @Column(name = "id_persona")
    private Integer persona;

    @Column(name = "id_team")
    private Integer team;

    @Column(name = "status")
    private Integer status;

}
