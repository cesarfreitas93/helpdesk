package pe.farell.go.backend.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pe.farell.go.backend.model.dto.request.ProjectRequestDto;
import pe.farell.go.backend.model.entity.ProjectEntity;
import pe.farell.go.backend.repository.ProjectRepository;
import pe.farell.go.backend.service.impl.ProjectService;

@ContextConfiguration(classes = {ProjectController.class, ProjectService.class, ModelMapper.class})
@ExtendWith(SpringExtension.class)
class ProjectControllerTest {
    @Autowired
    private ProjectController projectController;

    @MockBean
    private ProjectRepository projectRepository;

    /**
     * Method under test: {@link ProjectController#bloqueoTarjeta()}
     */
    @Test
    void testBloqueoTarjeta() throws Exception {
        when(projectRepository.findAll()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/v1/project/all");
        MockMvcBuilders.standaloneSetup(projectController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"code\":2,\"msg\":\"La consulta de projectos no retorno resultados\",\"data\":{\"content\":[]}}"));
    }

    /**
     * Method under test: {@link ProjectController#bloqueoTarjeta()}
     */
    @Test
    void testBloqueoTarjeta2() throws Exception {
        ProjectEntity projectEntity = new ProjectEntity();
        projectEntity.onPreUpdate();
        projectEntity.setConfig("?");
        projectEntity.setCreateAt(LocalDateTime.of(1, 1, 1, 1, 1));
        projectEntity.setCreateUser(1);
        projectEntity.setDescription("The characteristics of someone or something");
        projectEntity.setFiles("?");
        projectEntity.setId(1);
        projectEntity.setName("?");
        projectEntity.setStatus(1);
        projectEntity.setUpdateAt(LocalDateTime.of(1, 1, 1, 1, 1));
        projectEntity.setUpdateUser(1);

        ArrayList<ProjectEntity> projectEntityList = new ArrayList<>();
        projectEntityList.add(projectEntity);
        when(projectRepository.findAll()).thenReturn(projectEntityList);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/v1/project/all");
        MockMvcBuilders.standaloneSetup(projectController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"code\":1,\"msg\":\"SUCCESS\",\"data\":{\"content\":[{\"createAt\":[1,1,1,1,1],\"updateAt\":[1,1,1,1,1],\"createUser"
                                        + "\":1,\"updateUser\":1,\"id\":1,\"name\":\"?\",\"description\":\"The characteristics of someone or something\","
                                        + "\"status\":1,\"config\":\"?\",\"files\":\"?\"}]}}"));
    }

    /**
     * Method under test: {@link ProjectController#create(ProjectRequestDto)}
     */
    @Test
    void testCreate() throws Exception {
        ProjectRequestDto projectRequestDto = new ProjectRequestDto();
        projectRequestDto.setConfig("Config");
        projectRequestDto.setDescription("The characteristics of someone or something");
        projectRequestDto.setFiles("Files");
        projectRequestDto.setName("Name");
        projectRequestDto.setStatus(1);
        String content = (new ObjectMapper()).writeValueAsString(projectRequestDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/v1/project")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(projectController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(405));
    }

    /**
     * Method under test: {@link ProjectController#update(Integer, ProjectRequestDto)}
     */
    @Test
    void testUpdate() throws Exception {
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
        String content = (new ObjectMapper()).writeValueAsString(projectRequestDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/v1/project/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(projectController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"code\":1,\"msg\":\"SUCCESS\",\"data\":{\"createAt\":[1,1,1,1,1],\"updateAt\":[1,1,1,1,1],\"createUser\":1,"
                                        + "\"updateUser\":1,\"id\":1,\"name\":\"Name\",\"description\":\"The characteristics of someone or something\",\"status"
                                        + "\":1,\"config\":\"Config\",\"files\":\"Files\"}}"));
    }
}

