package pe.farell.go.backend.model.dto.response;

import lombok.Data;

@Data
public class UserDto {

    private Integer id;
    private String username;
    private String pass;
    private String config;
    private String role;
    private Integer status;

}
