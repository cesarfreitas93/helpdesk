package pe.farell.go.backend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.farell.go.backend.model.dto.response.Content;
import pe.farell.go.backend.model.dto.response.Response;
import pe.farell.go.backend.model.entity.CategoryEntity;
import pe.farell.go.backend.model.entity.StatusEntity;
import pe.farell.go.backend.repository.CategoryHuRepository;
import pe.farell.go.backend.repository.StatusRepository;
import pe.farell.go.backend.service.Parameters;
import pe.farell.go.backend.util.ResponseUtil;

import java.util.List;

@Service
public class ParametersService implements Parameters {

    @Autowired
    private CategoryHuRepository categoryHuRepository;

    @Autowired
    private StatusRepository statusRepository;

    @Override
    public Response<Content<StatusEntity>> getAllStatusTask() {
        List<StatusEntity> response = statusRepository.findAll();
        return ResponseUtil.validateList(response, "La consulta de estados no retorno resultados");
    }

    @Override
    public Response<Content<CategoryEntity>> getAllCategoriesHu() {
        List<CategoryEntity> response = categoryHuRepository.findAll();
        return ResponseUtil.validateList(response, "La consulta de categorías no retorno resultados");
    }
}
