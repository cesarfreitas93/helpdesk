package pe.farell.go.backend.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.farell.go.backend.model.dto.request.TaskRequestCreateDto;
import pe.farell.go.backend.model.dto.request.TicketRequestCreateDto;
import pe.farell.go.backend.model.dto.response.Content;
import pe.farell.go.backend.model.dto.response.Response;
import pe.farell.go.backend.model.dto.response.ResponseBase;
import pe.farell.go.backend.model.entity.TaskEntity;
import pe.farell.go.backend.model.entity.TicketEntity;
import pe.farell.go.backend.service.impl.TaskService;
import pe.farell.go.backend.service.impl.TicketService;

@RestController
@RequestMapping(value = "v1/ticket")
@Api(value = "Ticket Controller Rest", produces = "application/json", tags = {"Controlador de tickets"})
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @Autowired
    private TaskService taskService;

    @GetMapping("/sprint/{id}")
    @ApiOperation(value = "Consultar tickets por id de sprint")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Consultar tareas by sprint", response = TicketEntity.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Error en el servidor", response = ResponseBase.class) })
    public ResponseEntity<Response<TicketEntity>> getTicketBySprint(
            @ApiParam(example = "13") @PathVariable Integer id) {
        return new ResponseEntity<>(this.ticketService.getTicket(id), HttpStatus.OK);
    }

    @ApiOperation(value = "Registra un ticket", tags = {"Controlador de tickets"})
    @PostMapping()
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Registro completado", response = TicketEntity.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Error en el servidor", response = ResponseBase.class)})
    public ResponseEntity<Response<TicketEntity>> create(@RequestBody TicketRequestCreateDto createDto) {
        return new ResponseEntity<>(this.ticketService.saveTicket(createDto), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Actualiza un ticket", tags = {"Controlador de tickets"})
    @PutMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Actualiza un ticket", response = TicketEntity.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Error en el servidor", response = ResponseBase.class)})
    public ResponseEntity<Response<TicketEntity>> update(
            @PathVariable Integer id,
            @RequestBody TicketRequestCreateDto createDto) {
        return new ResponseEntity<>(this.ticketService.updateTicket(id, createDto), HttpStatus.CREATED);
    }


    @ApiOperation(value = "Registra una Tarea", tags = {"Controlador de tickets"})
    @PostMapping("/task")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Datos de la tarea registrados", response = TaskEntity.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Error en el servidor", response = ResponseBase.class)})
    public ResponseEntity<Response<TaskEntity>> createTask(@RequestBody TaskRequestCreateDto createDto) {
        return new ResponseEntity<>(this.taskService.save(createDto), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Actualiza una Tarea", tags = {"Controlador de tickets"})
    @PutMapping("/task/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Datos de la tarea actualizados", response = TaskEntity.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Error en el servidor", response = ResponseBase.class)})
    public ResponseEntity<Response<TaskEntity>> updateTask(
            @PathVariable Integer id,
            @RequestBody TaskRequestCreateDto createDto) {
        return new ResponseEntity<>(this.taskService.update(id, createDto), HttpStatus.CREATED);
    }


    @GetMapping("/task/{id}")
    @ApiOperation(value = "Consultar Tarea por id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Consulta de tare por id", response = TaskEntity.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Error en el servidor", response = ResponseBase.class) })
    public ResponseEntity<Response<TaskEntity>> getTaskById(
            @ApiParam(example = "13") @PathVariable Integer id) {
        return new ResponseEntity<>(this.taskService.getById(id), HttpStatus.OK);
    }

    @GetMapping("/project/{id}")
    @ApiOperation(value = "Consultar tickets por id de proyecto")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Consultar ticket y tareas by proyecto", response = TicketEntity.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Error en el servidor", response = ResponseBase.class) })
    public ResponseEntity<Response<Content<TicketEntity>>> getTicketByProject(
            @ApiParam(example = "13") @PathVariable Integer id) {
        return new ResponseEntity<>(this.ticketService.getTicketsByProject(id), HttpStatus.OK);
    }
}
