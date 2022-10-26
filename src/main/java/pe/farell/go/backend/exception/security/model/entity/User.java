package pe.farell.go.backend.exception.security.model.entity;

import pe.farell.go.backend.model.entity.AuditModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "T_USERS", schema = "tesis", uniqueConstraints = @UniqueConstraint(columnNames = "username"))
public class User extends AuditModel {

    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "T_USER_SEQUENCE_ID", sequenceName = "t_users_id_seq", allocationSize = 1, schema = "tesis")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "T_USER_SEQUENCE_ID")
    private Integer id;

    @Column(unique = true, name = "username")
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

    public User(String username, String password, String config, Integer status) {
        this.username = username;
        this.password = password;
        this.config = config;
        this.status = status;
    }

    public User() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfig() {
        return config;
    }

    public void setConfig(String config) {
        this.config = config;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Set<Rol> getRoles() {
        return roles;
    }

    public void setRoles(Set<Rol> roles) {
        this.roles = roles;
    }
}
