package pe.farell.go.backend.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "MT_STATUSTASK", schema = "tesis")
@Getter
@Setter
public class StatusEntity {

    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "T_MSTATUS_SEQUENCE_ID", sequenceName = "mt_statustask_id_seq", allocationSize = 1, schema = "tesis")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "T_MSTATUS_SEQUENCE_ID" )
    private Integer id;

    @Column(name = "name")
    private String name;

}
