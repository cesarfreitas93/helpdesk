package pe.farell.go.backend.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
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
    private String docnumber;

    @Column(name = "dateofbirth")
    private LocalDateTime dateOfBirth;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "id_user", referencedColumnName = "id")
    private UserEntity user;

}
