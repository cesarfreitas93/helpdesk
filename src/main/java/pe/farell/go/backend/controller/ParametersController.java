package pe.farell.go.backend.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.farell.go.backend.model.dto.response.Content;
import pe.farell.go.backend.model.dto.response.Response;
import pe.farell.go.backend.model.dto.response.ResponseBase;
import pe.farell.go.backend.model.entity.CategoryEntity;
import pe.farell.go.backend.model.entity.StatusEntity;
import pe.farell.go.backend.service.impl.ParametersService;

@RestController
@RequestMapping(value = "v1/parameter")
@Api(value = "Parameters Controller Rest", produces = "application/json", tags = {"Controlador de parámetros"})
public class ParametersController {

    @Autowired
    private ParametersService parametersService;

    @ApiOperation("Consulta de estados de tareas en lista")
    @GetMapping("/status/all")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok", response = ResponseBase.class),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Error en el servidor", response = ResponseBase.class)})
    public ResponseEntity<Response<Content<StatusEntity>>> getAllStatusTask() {
        return ResponseEntity.ok(this.parametersService.getAllStatusTask());
    }

    @ApiOperation("Consulta de categorias de historias de usuario")
    @GetMapping("/category/all")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok", response = ResponseBase.class),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Error en el servidor", response = ResponseBase.class)})
    public ResponseEntity<Response<Content<CategoryEntity>>> getAllCategoriesHu() {
        return ResponseEntity.ok(this.parametersService.getAllCategoriesHu());
    }
}
