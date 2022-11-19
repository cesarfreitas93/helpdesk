package pe.farell.go.backend.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.farell.go.backend.model.dto.request.PersonRequestDto;
import pe.farell.go.backend.model.dto.response.Content;
import pe.farell.go.backend.model.dto.response.Response;
import pe.farell.go.backend.model.dto.response.ResponseBase;
import pe.farell.go.backend.model.entity.PersonEntity;
import pe.farell.go.backend.model.entity.ProjectEntity;
import pe.farell.go.backend.model.entity.TicketEntity;
import pe.farell.go.backend.service.impl.PersonService;

@RestController
@RequestMapping(value = "v1/person")
@Api(value = "Persona Controller Rest", produces = "application/json", tags = {"Controlador de personas"})
public class PersonController {

    @Autowired
    private PersonService personService;

    @ApiOperation("Consulta de todas las personas del sistema")
    @GetMapping("/all")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok", response = ResponseBase.class),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Error en el servidor", response = ResponseBase.class)})
    public ResponseEntity<Response<Content<PersonEntity>>> getAllPersons() {
        return ResponseEntity.ok(this.personService.getAllPersons());
    }

    @ApiOperation("Registra un nuevo usuario")
    @PostMapping()
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Registro completado", response = TicketEntity.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Error en el servidor", response = ResponseBase.class)})
    public ResponseEntity<Response<PersonEntity>> create(@RequestBody PersonRequestDto createDto) {
        return new ResponseEntity<>(this.personService.save(createDto), HttpStatus.CREATED);
    }

}
