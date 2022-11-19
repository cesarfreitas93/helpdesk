package pe.farell.go.backend.model.entity;

import lombok.Getter;
import lombok.Setter;
import pe.farell.go.backend.security.model.entity.Rol;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

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

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "IT_ROLE_USER", schema = "tesis", joinColumns = @JoinColumn(name = "id_user"),
            inverseJoinColumns = @JoinColumn(name = "id_rol"))
    private Set<Rol> roles = new HashSet<>();
}
