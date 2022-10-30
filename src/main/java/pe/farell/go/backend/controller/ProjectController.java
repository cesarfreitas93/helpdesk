package pe.farell.go.backend.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.farell.go.backend.model.dto.request.ProjectRequestDto;
import pe.farell.go.backend.model.dto.request.TicketRequestCreateDto;
import pe.farell.go.backend.model.dto.response.Content;
import pe.farell.go.backend.model.dto.response.Response;
import pe.farell.go.backend.model.dto.response.ResponseBase;
import pe.farell.go.backend.model.entity.ProjectEntity;
import pe.farell.go.backend.model.entity.TicketEntity;
import pe.farell.go.backend.service.impl.ProjectService;

@RestController
@RequestMapping(value = "v1/project")
@Api(value = "Projects Controller Rest", produces = "application/json", tags = {"Controlador de proyectos"})
public class ProjectController {

    @Autowired
    private ProjectService projectService;


    @ApiOperation("Consulta de todos las proyectos del sistema")
    @GetMapping("/all")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok", response = ResponseBase.class),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Error en el servidor", response = ResponseBase.class)})
    public ResponseEntity<Response<Content<ProjectEntity>>> bloqueoTarjeta() {
        return ResponseEntity.ok(this.projectService.getAll());
    }

    @ApiOperation("Registra un proyecto")
    @PostMapping()
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Registro completado", response = TicketEntity.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Error en el servidor", response = ResponseBase.class)})
    public ResponseEntity<Response<ProjectEntity>> create(@RequestBody ProjectRequestDto createDto) {
        return new ResponseEntity<>(this.projectService.save(createDto), HttpStatus.CREATED);
    }

    @ApiOperation("Actualiza un proyecto")
    @PutMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Actualiza un Proyecto", response = TicketEntity.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Error en el servidor", response = ResponseBase.class)})
    public ResponseEntity<Response<ProjectEntity>> update(
            @PathVariable Integer id,
            @RequestBody ProjectRequestDto createDto) {
        return new ResponseEntity<>(this.projectService.update(id, createDto), HttpStatus.CREATED);
    }

}
