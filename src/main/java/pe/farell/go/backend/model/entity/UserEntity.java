package pe.farell.go.backend.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "T_USERS", schema = "tesis", uniqueConstraints = @UniqueConstraint(columnNames = "username"))
@Getter
@Setter
public class UserEntity extends AuditModel {
    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "T_USER_SEQUENCE_ID", sequenceName = "t_users_id_seq", allocationSize = 1, schema = "tesis")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "T_USER_SEQUENCE_ID")
    private Integer id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "config")
    private String config;

    @Column(name = "status")
    private Integer status;

    @OneToOne(mappedBy = "user")
    private PersonEntity person;
}
