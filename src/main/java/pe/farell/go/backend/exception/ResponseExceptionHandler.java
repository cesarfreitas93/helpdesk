package pe.farell.go.backend.exception;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import pe.farell.go.backend.constant.EnumResponse;
import pe.farell.go.backend.model.dto.response.ResponseBase;

@ControllerAdvice
@RestController
@Slf4j
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        String msgErr = "";
        for (ObjectError objErr : ex.getBindingResult().getAllErrors()) {
            FieldError fieldError = (FieldError) objErr;
            msgErr = msgErr.concat(fieldError.getField()).concat(" ").concat(objErr.getDefaultMessage()).concat(" / ");
        }
        ResponseBase responseDto = new ResponseBase();
        responseDto.setMsg(msgErr);
        responseDto.setCode(EnumResponse.C000.getCode());
        log.info("REST API TESIS ERROR BAD_REQUEST: {}", msgErr);
        return new ResponseEntity<>(responseDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ResponseBase> manejarModeloExcepciones(Exception ex, WebRequest request) {
        ResponseBase responseDto = new ResponseBase();
        responseDto.setMsg(ex.getMessage());
        responseDto.setCode(EnumResponse.C000.getCode());
        log.info("REST API TESIS ERROR INTERNAL_SERVER_ERROR: {}", ex.getMessage());
        return new ResponseEntity<>(responseDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ModelNotFoundException.class)
    public final ResponseEntity<ResponseBase> manejarModeloExcepciones(ModelNotFoundException ex, WebRequest request) {
        ResponseBase responseDto = new ResponseBase();
        responseDto.setMsg(ex.getMessage());
        responseDto.setCode(EnumResponse.C002.getCode());
        log.info("REST API TESIS ERROR NOT_FOUND: {}", ex.getMessage());
        return new ResponseEntity<>(responseDto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ValidationException.class)
    public final ResponseEntity<ResponseBase> handleValidationException(ValidationException ex, WebRequest request) {
        ResponseBase responseDto = new ResponseBase();
        responseDto.setCode(ex.getCodigo());
        responseDto.setMsg(ex.getMessage());
        return new ResponseEntity<>(responseDto, HttpStatus.BAD_REQUEST);
    }
}
