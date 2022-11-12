package pe.farell.go.backend.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.farell.go.backend.model.dto.request.SprintRequestCreateDto;
import pe.farell.go.backend.model.dto.response.Content;
import pe.farell.go.backend.model.dto.response.Response;
import pe.farell.go.backend.model.dto.response.ResponseBase;
import pe.farell.go.backend.model.entity.ProjectEntity;
import pe.farell.go.backend.model.entity.SprintEntity;
import pe.farell.go.backend.service.impl.SprintService;

@RestController
@RequestMapping(value = "v1/sprint")
@Api(value = "Sprint Controller Rest", produces = "application/json", tags = {"Controlador de Sprints"})
public class SprintController {

    @Autowired
    private SprintService sprintService;

    @ApiOperation(value = "Registra un sprint", tags = {"Controlador de Sprints"})
    @PostMapping()
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Registro completado", response = SprintEntity.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Error en el servidor", response = ResponseBase.class)})
    public ResponseEntity<Response<SprintEntity>> create(@RequestBody SprintRequestCreateDto createDto) {
        return new ResponseEntity<>(this.sprintService.save(createDto), HttpStatus.CREATED);
    }

    @ApiOperation("Consulta de todos las proyectos del sistema")
    @GetMapping("/all")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok", response = ResponseBase.class),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Error en el servidor", response = ResponseBase.class)})
    public ResponseEntity<Response<Content<SprintEntity>>> bloqueoTarjeta() {
        return ResponseEntity.ok(this.sprintService.getAll());
    }

    @ApiOperation("Consultar grafico pastel de tareas por sprint")
    @GetMapping("/all")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok", response = ResponseBase.class),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Error en el servidor", response = ResponseBase.class)})
    public ResponseEntity<Response<Content<SprintEntity>>> getPieChartDataBySprint() {
        return ResponseEntity.ok(this.sprintService.getAll());
    }


}
