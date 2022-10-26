package pe.farell.go.backend.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EnumResponse {

    C000(0, "BAD"),
    C001(1, "SUCCESS"),
    C002(2, "EMPTY"),
    C003(3, "ALERT");

    private Integer code;
    private String msg;

}
