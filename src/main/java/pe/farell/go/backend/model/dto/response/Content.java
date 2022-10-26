package pe.farell.go.backend.model.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Content<T> {

    private List<T> content;

}
