package pe.farell.go.backend.controller;

import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.farell.go.backend.model.dto.response.Content;
import pe.farell.go.backend.model.dto.response.Response;
import pe.farell.go.backend.model.dto.response.ResponseBase;
import pe.farell.go.backend.model.dto.response.ReportTaskView;
import pe.farell.go.backend.model.entity.TicketEntity;
import pe.farell.go.backend.service.impl.TaskService;


@RestController
@RequestMapping(value = "v1/report")
@Api(value = "Report Controller Rest", produces = "application/json", tags = {"Controlador de reportes"})
public class ReportController {

    @Autowired
    private TaskService taskService;

    @GetMapping("/tasks/sprint/{id}")
    @ApiOperation(value = "Consultar reporte de tareas por id de sprint")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Consultar tareas by sprint", response = TicketEntity.class),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Error en el servidor", response = ResponseBase.class) })
    public ResponseEntity<Response<Content<ReportTaskView>>> getTicketBySprint(
            @ApiParam(example = "13") @PathVariable Integer id) {
        return new ResponseEntity<>(this.taskService.getReportTaskBySprint(id), HttpStatus.OK);
    }
}
