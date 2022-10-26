package pe.farell.go.backend.model.dto.response;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class Response<T> extends ResponseBase {

    private T data;

}