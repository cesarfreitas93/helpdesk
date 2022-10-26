package pe.farell.go.backend.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "T_PERSON", schema = "tesis")
@Getter
@Setter
public class PersonEntity {
    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "T_PERSON_SEQUENCE_ID", sequenceName = "t_person_id_seq", allocationSize = 1, schema = "tesis")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "T_PERSON_SEQUENCE_ID" )
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "doctype")
    private Integer doctype;

    @Column(name = "docnumber")
    private Integer docnumber;

    @Column(name = "dateofbirth")
    private LocalDateTime dateOfBirth;

    @OneToOne(cascade = CascadeType.ALL)
    @JsonBackReference
    @JoinColumn(name = "id_user", referencedColumnName = "id")
    private UserEntity user;

}
