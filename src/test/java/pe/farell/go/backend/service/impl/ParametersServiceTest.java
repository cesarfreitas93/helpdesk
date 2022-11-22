package pe.farell.go.backend.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pe.farell.go.backend.model.dto.response.Content;
import pe.farell.go.backend.model.dto.response.Response;
import pe.farell.go.backend.model.entity.CategoryEntity;
import pe.farell.go.backend.model.entity.StatusEntity;
import pe.farell.go.backend.repository.CategoryHuRepository;
import pe.farell.go.backend.repository.StatusRepository;

@ContextConfiguration(classes = {ParametersService.class})
@ExtendWith(SpringExtension.class)
class ParametersServiceTest {
    @MockBean
    private CategoryHuRepository categoryHuRepository;

    @Autowired
    private ParametersService parametersService;

    @MockBean
    private StatusRepository statusRepository;

    /**
     * Method under test: {@link ParametersService#getAllStatusTask()}
     */
    @Test
    void testGetAllStatusTask() {
        when(statusRepository.findAll()).thenReturn(new ArrayList<>());
        Response<Content<StatusEntity>> actualAllStatusTask = parametersService.getAllStatusTask();
        assertEquals(2, actualAllStatusTask.getCode().intValue());
        assertEquals("La consulta de estados no retorno resultados", actualAllStatusTask.getMsg());
        assertTrue(actualAllStatusTask.getData().getContent().isEmpty());
        verify(statusRepository).findAll();
    }

    /**
     * Method under test: {@link ParametersService#getAllStatusTask()}
     */
    @Test
    void testGetAllStatusTask2() {
        StatusEntity statusEntity = new StatusEntity();
        statusEntity.setId(1);
        statusEntity.setName("La consulta de estados no retorno resultados");

        ArrayList<StatusEntity> statusEntityList = new ArrayList<>();
        statusEntityList.add(statusEntity);
        when(statusRepository.findAll()).thenReturn(statusEntityList);
        Response<Content<StatusEntity>> actualAllStatusTask = parametersService.getAllStatusTask();
        assertEquals(1, actualAllStatusTask.getCode().intValue());
        assertEquals("SUCCESS", actualAllStatusTask.getMsg());
        assertEquals(1, actualAllStatusTask.getData().getContent().size());
        verify(statusRepository).findAll();
    }

    /**
     * Method under test: {@link ParametersService#getAllCategoriesHu()}
     */
    @Test
    void testGetAllCategoriesHu() {
        when(categoryHuRepository.findAll()).thenReturn(new ArrayList<>());
        Response<Content<CategoryEntity>> actualAllCategoriesHu = parametersService.getAllCategoriesHu();
        assertEquals(2, actualAllCategoriesHu.getCode().intValue());
        assertEquals("La consulta de categorías no retorno resultados", actualAllCategoriesHu.getMsg());
        assertTrue(actualAllCategoriesHu.getData().getContent().isEmpty());
        verify(categoryHuRepository).findAll();
    }

    /**
     * Method under test: {@link ParametersService#getAllCategoriesHu()}
     */
    @Test
    void testGetAllCategoriesHu2() {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setConfig("La consulta de categorías no retorno resultados");
        categoryEntity.setId(1);
        categoryEntity.setName("La consulta de categorías no retorno resultados");

        ArrayList<CategoryEntity> categoryEntityList = new ArrayList<>();
        categoryEntityList.add(categoryEntity);
        when(categoryHuRepository.findAll()).thenReturn(categoryEntityList);
        Response<Content<CategoryEntity>> actualAllCategoriesHu = parametersService.getAllCategoriesHu();
        assertEquals(1, actualAllCategoriesHu.getCode().intValue());
        assertEquals("SUCCESS", actualAllCategoriesHu.getMsg());
        assertEquals(1, actualAllCategoriesHu.getData().getContent().size());
        verify(categoryHuRepository).findAll();
    }
}

