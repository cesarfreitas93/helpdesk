package pe.farell.go.backend.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(
        value = {"createAt",
                "updateAt",
                "createUser",
                "updateUser"},
        allowGetters = true
)
public abstract class AuditModel implements Serializable {

    @Column(name = "create_at", nullable = false, updatable = false)
    @CreatedBy
    private LocalDateTime createAt;

    @Column(name = "update_at")
    @LastModifiedBy
    private LocalDateTime updateAt;

    @Column(name = "create_user")
    private Integer createUser;

    @Column(name = "update_user")
    private Integer updateUser;

    @PrePersist
    public void onPrePersist() {
        this.createAt = LocalDateTime.now();
    }

    @PreUpdate
    public void onPreUpdate() {
        this.updateAt = LocalDateTime.now();
    }
}
