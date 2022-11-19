package pe.farell.go.backend.model.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
public class PersonRequestDto implements Serializable {
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    private Set<String> roles = new HashSet<>();
    private String name;
    private Integer doctype;
    private String docnumber;
    private LocalDateTime dateOfBirth;
}
