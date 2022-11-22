package pe.farell.go.backend.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pe.farell.go.backend.exception.ValidationException;
import pe.farell.go.backend.model.dto.request.ProjectRequestDto;
import pe.farell.go.backend.model.dto.response.Content;
import pe.farell.go.backend.model.dto.response.Response;
import pe.farell.go.backend.model.entity.ProjectEntity;
import pe.farell.go.backend.repository.ProjectRepository;

@ContextConfiguration(classes = {ProjectService.class})
@ExtendWith(SpringExtension.class)
class ProjectServiceTest {
    @MockBean
    private ModelMapper modelMapper;

    @MockBean
    private ProjectRepository projectRepository;

    @Autowired
    private ProjectService projectService;

    /**
     * Method under test: {@link ProjectService#save(ProjectRequestDto)}
     */
    @Test
    void testSave() {
        doNothing().when(modelMapper).map((Object) any(), (Object) any());

        ProjectEntity projectEntity = new ProjectEntity();
        projectEntity.onPreUpdate();
        projectEntity.setConfig("Config");
        projectEntity.setCreateAt(LocalDateTime.of(1, 1, 1, 1, 1));
        projectEntity.setCreateUser(1);
        projectEntity.setDescription("The characteristics of someone or something");
        projectEntity.setFiles("Files");
        projectEntity.setId(1);
        projectEntity.setName("Name");
        projectEntity.setStatus(1);
        projectEntity.setUpdateAt(LocalDateTime.of(1, 1, 1, 1, 1));
        projectEntity.setUpdateUser(1);
        when(projectRepository.save((ProjectEntity) any())).thenReturn(projectEntity);

        ProjectRequestDto projectRequestDto = new ProjectRequestDto();
        projectRequestDto.setConfig("Config");
        projectRequestDto.setDescription("The characteristics of someone or something");
        projectRequestDto.setFiles("Files");
        projectRequestDto.setName("Name");
        projectRequestDto.setStatus(1);
        Response<ProjectEntity> actualSaveResult = projectService.save(projectRequestDto);
        assertEquals(1, actualSaveResult.getCode().intValue());
        assertEquals("SUCCESS", actualSaveResult.getMsg());
        assertSame(projectEntity, actualSaveResult.getData());
        verify(modelMapper).map((Object) any(), (Object) any());
        verify(projectRepository).save((ProjectEntity) any());
    }

    /**
     * Method under test: {@link ProjectService#save(ProjectRequestDto)}
     */
    @Test
    void testSave2() {
        doNothing().when(modelMapper).map((Object) any(), (Object) any());
        when(projectRepository.save((ProjectEntity) any())).thenThrow(new ValidationException(1, "Mensaje"));

        ProjectRequestDto projectRequestDto = new ProjectRequestDto();
        projectRequestDto.setConfig("Config");
        projectRequestDto.setDescription("The characteristics of someone or something");
        projectRequestDto.setFiles("Files");
        projectRequestDto.setName("Name");
        projectRequestDto.setStatus(1);
        assertThrows(ValidationException.class, () -> projectService.save(projectRequestDto));
        verify(modelMapper).map((Object) any(), (Object) any());
        verify(projectRepository).save((ProjectEntity) any());
    }

    /**
     * Method under test: {@link ProjectService#update(Integer, ProjectRequestDto)}
     */
    @Test
    void testUpdate() {
        doNothing().when(modelMapper).map((Object) any(), (Object) any());

        ProjectEntity projectEntity = new ProjectEntity();
        projectEntity.onPreUpdate();
        projectEntity.setConfig("Config");
        projectEntity.setCreateAt(LocalDateTime.of(1, 1, 1, 1, 1));
        projectEntity.setCreateUser(1);
        projectEntity.setDescription("The characteristics of someone or something");
        projectEntity.setFiles("Files");
        projectEntity.setId(1);
        projectEntity.setName("Name");
        projectEntity.setStatus(1);
        projectEntity.setUpdateAt(LocalDateTime.of(1, 1, 1, 1, 1));
        projectEntity.setUpdateUser(1);
        Optional<ProjectEntity> ofResult = Optional.of(projectEntity);

        ProjectEntity projectEntity1 = new ProjectEntity();
        projectEntity1.onPreUpdate();
        projectEntity1.setConfig("Config");
        projectEntity1.setCreateAt(LocalDateTime.of(1, 1, 1, 1, 1));
        projectEntity1.setCreateUser(1);
        projectEntity1.setDescription("The characteristics of someone or something");
        projectEntity1.setFiles("Files");
        projectEntity1.setId(1);
        projectEntity1.setName("Name");
        projectEntity1.setStatus(1);
        projectEntity1.setUpdateAt(LocalDateTime.of(1, 1, 1, 1, 1));
        projectEntity1.setUpdateUser(1);
        when(projectRepository.save((ProjectEntity) any())).thenReturn(projectEntity1);
        when(projectRepository.findById((Integer) any())).thenReturn(ofResult);

        ProjectRequestDto projectRequestDto = new ProjectRequestDto();
        projectRequestDto.setConfig("Config");
        projectRequestDto.setDescription("The characteristics of someone or something");
        projectRequestDto.setFiles("Files");
        projectRequestDto.setName("Name");
        projectRequestDto.setStatus(1);
        Response<ProjectEntity> actualUpdateResult = projectService.update(1, projectRequestDto);
        assertEquals(1, actualUpdateResult.getCode().intValue());
        assertEquals("SUCCESS", actualUpdateResult.getMsg());
        assertSame(projectEntity1, actualUpdateResult.getData());
        verify(modelMapper).map((Object) any(), (Object) any());
        verify(projectRepository).save((ProjectEntity) any());
        verify(projectRepository).findById((Integer) any());
    }

    /**
     * Method under test: {@link ProjectService#update(Integer, ProjectRequestDto)}
     */
    @Test
    void testUpdate2() {
        doNothing().when(modelMapper).map((Object) any(), (Object) any());

        ProjectEntity projectEntity = new ProjectEntity();
        projectEntity.onPreUpdate();
        projectEntity.setConfig("Config");
        projectEntity.setCreateAt(LocalDateTime.of(1, 1, 1, 1, 1));
        projectEntity.setCreateUser(1);
        projectEntity.setDescription("The characteristics of someone or something");
        projectEntity.setFiles("Files");
        projectEntity.setId(1);
        projectEntity.setName("Name");
        projectEntity.setStatus(1);
        projectEntity.setUpdateAt(LocalDateTime.of(1, 1, 1, 1, 1));
        projectEntity.setUpdateUser(1);
        Optional<ProjectEntity> ofResult = Optional.of(projectEntity);
        when(projectRepository.save((ProjectEntity) any())).thenThrow(new ValidationException(1, "Mensaje"));
        when(projectRepository.findById((Integer) any())).thenReturn(ofResult);

        ProjectRequestDto projectRequestDto = new ProjectRequestDto();
        projectRequestDto.setConfig("Config");
        projectRequestDto.setDescription("The characteristics of someone or something");
        projectRequestDto.setFiles("Files");
        projectRequestDto.setName("Name");
        projectRequestDto.setStatus(1);
        assertThrows(ValidationException.class, () -> projectService.update(1, projectRequestDto));
        verify(modelMapper).map((Object) any(), (Object) any());
        verify(projectRepository).save((ProjectEntity) any());
        verify(projectRepository).findById((Integer) any());
    }

    /**
     * Method under test: {@link ProjectService#update(Integer, ProjectRequestDto)}
     */
    @Test
    void testUpdate3() {
        doNothing().when(modelMapper).map((Object) any(), (Object) any());

        ProjectEntity projectEntity = new ProjectEntity();
        projectEntity.onPreUpdate();
        projectEntity.setConfig("Config");
        projectEntity.setCreateAt(LocalDateTime.of(1, 1, 1, 1, 1));
        projectEntity.setCreateUser(1);
        projectEntity.setDescription("The characteristics of someone or something");
        projectEntity.setFiles("Files");
        projectEntity.setId(1);
        projectEntity.setName("Name");
        projectEntity.setStatus(1);
        projectEntity.setUpdateAt(LocalDateTime.of(1, 1, 1, 1, 1));
        projectEntity.setUpdateUser(1);
        when(projectRepository.save((ProjectEntity) any())).thenReturn(projectEntity);
        when(projectRepository.findById((Integer) any())).thenReturn(Optional.empty());

        ProjectRequestDto projectRequestDto = new ProjectRequestDto();
        projectRequestDto.setConfig("Config");
        projectRequestDto.setDescription("The characteristics of someone or something");
        projectRequestDto.setFiles("Files");
        projectRequestDto.setName("Name");
        projectRequestDto.setStatus(1);
        assertThrows(ValidationException.class, () -> projectService.update(1, projectRequestDto));
        verify(projectRepository).findById((Integer) any());
    }

    /**
     * Method under test: {@link ProjectService#getById(Integer)}
     */
    @Test
    void testGetById() {
        ProjectEntity projectEntity = new ProjectEntity();
        projectEntity.onPreUpdate();
        projectEntity.setConfig("Config");
        projectEntity.setCreateAt(LocalDateTime.of(1, 1, 1, 1, 1));
        projectEntity.setCreateUser(1);
        projectEntity.setDescription("The characteristics of someone or something");
        projectEntity.setFiles("Files");
        projectEntity.setId(1);
        projectEntity.setName("Name");
        projectEntity.setStatus(1);
        projectEntity.setUpdateAt(LocalDateTime.of(1, 1, 1, 1, 1));
        projectEntity.setUpdateUser(1);
        Optional<ProjectEntity> ofResult = Optional.of(projectEntity);
        when(projectRepository.findById((Integer) any())).thenReturn(ofResult);
        Response<ProjectEntity> actualById = projectService.getById(1);
        assertEquals(1, actualById.getCode().intValue());
        assertEquals("SUCCESS", actualById.getMsg());
        assertSame(projectEntity, actualById.getData());
        verify(projectRepository).findById((Integer) any());
    }

    /**
     * Method under test: {@link ProjectService#getById(Integer)}
     */
    @Test
    void testGetById2() {
        when(projectRepository.findById((Integer) any())).thenReturn(Optional.empty());
        assertThrows(ValidationException.class, () -> projectService.getById(1));
        verify(projectRepository).findById((Integer) any());
    }

    /**
     * Method under test: {@link ProjectService#getById(Integer)}
     */
    @Test
    void testGetById3() {
        when(projectRepository.findById((Integer) any()))
                .thenThrow(new ValidationException(1, "El proyecto que desea abrir no existe."));
        assertThrows(ValidationException.class, () -> projectService.getById(1));
        verify(projectRepository).findById((Integer) any());
    }

    /**
     * Method under test: {@link ProjectService#getAll()}
     */
    @Test
    void testGetAll() {
        when(projectRepository.findAll()).thenReturn(new ArrayList<>());
        Response<Content<ProjectEntity>> actualAll = projectService.getAll();
        assertEquals(2, actualAll.getCode().intValue());
        assertEquals("La consulta de projectos no retorno resultados", actualAll.getMsg());
        assertTrue(actualAll.getData().getContent().isEmpty());
        verify(projectRepository).findAll();
    }

    /**
     * Method under test: {@link ProjectService#getAll()}
     */
    @Test
    void testGetAll2() {
        ProjectEntity projectEntity = new ProjectEntity();
        projectEntity.onPreUpdate();
        projectEntity.setConfig("La consulta de projectos no retorno resultados");
        projectEntity.setCreateAt(LocalDateTime.of(1, 1, 1, 1, 1));
        projectEntity.setCreateUser(1);
        projectEntity.setDescription("The characteristics of someone or something");
        projectEntity.setFiles("La consulta de projectos no retorno resultados");
        projectEntity.setId(1);
        projectEntity.setName("La consulta de projectos no retorno resultados");
        projectEntity.setStatus(1);
        projectEntity.setUpdateAt(LocalDateTime.of(1, 1, 1, 1, 1));
        projectEntity.setUpdateUser(1);

        ArrayList<ProjectEntity> projectEntityList = new ArrayList<>();
        projectEntityList.add(projectEntity);
        when(projectRepository.findAll()).thenReturn(projectEntityList);
        Response<Content<ProjectEntity>> actualAll = projectService.getAll();
        assertEquals(1, actualAll.getCode().intValue());
        assertEquals("SUCCESS", actualAll.getMsg());
        assertEquals(1, actualAll.getData().getContent().size());
        verify(projectRepository).findAll();
    }

    /**
     * Method under test: {@link ProjectService#getAll()}
     */
    @Test
    void testGetAll3() {
        when(projectRepository.findAll())
                .thenThrow(new ValidationException(1, "La consulta de projectos no retorno resultados"));
        assertThrows(ValidationException.class, () -> projectService.getAll());
        verify(projectRepository).findAll();
    }
}

