package pe.farell.go.backend.service;

import pe.farell.go.backend.model.dto.response.Content;
import pe.farell.go.backend.model.dto.response.Response;
import pe.farell.go.backend.model.entity.CategoryEntity;
import pe.farell.go.backend.model.entity.StatusEntity;

public interface Parameters {
    Response<Content<StatusEntity>> getAllStatusTask();
    Response<Content<CategoryEntity>> getAllCategoriesHu();
}
