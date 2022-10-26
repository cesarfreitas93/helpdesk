package pe.farell.go.backend.exception;

import lombok.Getter;

@Getter
public class ValidationException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private final Integer codigo;

    public ValidationException(Integer codigo, String mensaje) {
        super(mensaje);
        this.codigo = codigo;
    }
}
