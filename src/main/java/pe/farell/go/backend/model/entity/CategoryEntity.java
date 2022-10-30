package pe.farell.go.backend.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "MT_CATHU", schema = "tesis")
@Getter
@Setter
public class CategoryEntity {
    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "T_MCAT_SEQUENCE_ID", sequenceName = "mt_cathu_id_seq", allocationSize = 1, schema = "tesis")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "T_MCAT_SEQUENCE_ID" )
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "config")
    private String config;
}
