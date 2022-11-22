package pe.farell.go.backend.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EnumStatus {
    S003(3, "Nuevo"),
    S001(1, "En Progreso"),
    S005(5, "Completado");

    private Integer code;
    private String msg;
}
