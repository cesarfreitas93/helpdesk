package pe.farell.go.backend.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDateTime;
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
import pe.farell.go.backend.model.dto.request.TaskRequestCreateDto;
import pe.farell.go.backend.model.dto.request.TicketRequestCreateDto;
import pe.farell.go.backend.model.entity.TaskEntity;
import pe.farell.go.backend.model.entity.TicketEntity;
import pe.farell.go.backend.repository.SprintRepository;
import pe.farell.go.backend.repository.TaskRepository;
import pe.farell.go.backend.repository.TicketRepository;
import pe.farell.go.backend.service.impl.TaskService;
import pe.farell.go.backend.service.impl.TicketService;

@ContextConfiguration(classes = {TicketController.class, TaskService.class, TicketService.class, ModelMapper.class})
@ExtendWith(SpringExtension.class)
class TicketControllerTest {
    @MockBean
    private SprintRepository sprintRepository;

    @MockBean
    private TaskRepository taskRepository;

    @Autowired
    private TicketController ticketController;

    @MockBean
    private TicketRepository ticketRepository;

    /**
     * Method under test: {@link TicketController#create(TicketRequestCreateDto)}
     */
    @Test
    void testCreate() throws Exception {
        TicketRequestCreateDto ticketRequestCreateDto = new TicketRequestCreateDto();
        ticketRequestCreateDto.setAssignedTo(1);
        ticketRequestCreateDto.setCategory(1);
        ticketRequestCreateDto.setDescription("The characteristics of someone or something");
        ticketRequestCreateDto.setName("Name");
        ticketRequestCreateDto.setProject(1);
        ticketRequestCreateDto.setSprint(1);
        ticketRequestCreateDto.setStatus(1);
        ticketRequestCreateDto.setWeight(3);
        String content = (new ObjectMapper()).writeValueAsString(ticketRequestCreateDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/v1/ticket")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(ticketController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(405));
    }

    /**
     * Method under test: {@link TicketController#createTask(TaskRequestCreateDto)}
     */
    @Test
    void testCreateTask() throws Exception {
        TaskRequestCreateDto taskRequestCreateDto = new TaskRequestCreateDto();
        taskRequestCreateDto.setAssignedto(1);
        taskRequestCreateDto.setCompleteAt(LocalDateTime.of(1, 1, 1, 1, 1));
        taskRequestCreateDto.setCompleteUser(1);
        taskRequestCreateDto.setDescription("The characteristics of someone or something");
        taskRequestCreateDto.setFiles("Files");
        taskRequestCreateDto.setId(1);
        taskRequestCreateDto.setName("Name");
        taskRequestCreateDto.setStatus(1);
        taskRequestCreateDto.setTicket(1);
        String content = (new ObjectMapper()).writeValueAsString(taskRequestCreateDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/v1/ticket/task")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(ticketController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }

    /**
     * Method under test: {@link TicketController#createTask(TaskRequestCreateDto)}
     */
    @Test
    void testCreateTask2() throws Exception {
        TaskEntity taskEntity = new TaskEntity();
        taskEntity.onPreUpdate();
        taskEntity.setAssignedto(1);
        taskEntity.setCompleteAt(LocalDateTime.of(1, 1, 1, 1, 1));
        taskEntity.setCompleteUser(1);
        taskEntity.setCreateAt(LocalDateTime.of(1, 1, 1, 1, 1));
        taskEntity.setCreateUser(1);
        taskEntity.setDescription("The characteristics of someone or something");
        taskEntity.setFiles("Files");
        taskEntity.setId(1);
        taskEntity.setName("Name");
        taskEntity.setStatus(1);
        taskEntity.setTicket(1);
        taskEntity.setUpdateAt(LocalDateTime.of(1, 1, 1, 1, 1));
        taskEntity.setUpdateUser(1);
        when(taskRepository.save((TaskEntity) any())).thenReturn(taskEntity);

        TaskRequestCreateDto taskRequestCreateDto = new TaskRequestCreateDto();
        taskRequestCreateDto.setAssignedto(1);
        taskRequestCreateDto.setCompleteAt(null);
        taskRequestCreateDto.setCompleteUser(1);
        taskRequestCreateDto.setDescription("The characteristics of someone or something");
        taskRequestCreateDto.setFiles("Files");
        taskRequestCreateDto.setId(1);
        taskRequestCreateDto.setName("Name");
        taskRequestCreateDto.setStatus(1);
        taskRequestCreateDto.setTicket(1);
        String content = (new ObjectMapper()).writeValueAsString(taskRequestCreateDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/v1/ticket/task")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(ticketController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"code\":1,\"msg\":\"SUCCESS\",\"data\":{\"createAt\":[1,1,1,1,1],\"updateAt\":[1,1,1,1,1],\"createUser\":1,"
                                        + "\"updateUser\":1,\"id\":1,\"name\":\"Name\",\"description\":\"The characteristics of someone or something\",\"status"
                                        + "\":1,\"assignedto\":1,\"files\":\"Files\",\"ticket\":1,\"completeAt\":[1,1,1,1,1],\"completeUser\":1}}"));
    }

    /**
     * Method under test: {@link TicketController#getTaskById(Integer)}
     */
    @Test
    void testGetTaskById() throws Exception {
        TaskEntity taskEntity = new TaskEntity();
        taskEntity.onPreUpdate();
        taskEntity.setAssignedto(1);
        taskEntity.setCompleteAt(LocalDateTime.of(1, 1, 1, 1, 1));
        taskEntity.setCompleteUser(1);
        taskEntity.setCreateAt(LocalDateTime.of(1, 1, 1, 1, 1));
        taskEntity.setCreateUser(1);
        taskEntity.setDescription("The characteristics of someone or something");
        taskEntity.setFiles("Files");
        taskEntity.setId(1);
        taskEntity.setName("Name");
        taskEntity.setStatus(1);
        taskEntity.setTicket(1);
        taskEntity.setUpdateAt(LocalDateTime.of(1, 1, 1, 1, 1));
        taskEntity.setUpdateUser(1);
        Optional<TaskEntity> ofResult = Optional.of(taskEntity);
        when(taskRepository.findById((Integer) any())).thenReturn(ofResult);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/v1/ticket/task/{id}", 1);
        MockMvcBuilders.standaloneSetup(ticketController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"code\":1,\"msg\":\"SUCCESS\",\"data\":{\"createAt\":[1,1,1,1,1],\"updateAt\":[1,1,1,1,1],\"createUser\":1,"
                                        + "\"updateUser\":1,\"id\":1,\"name\":\"Name\",\"description\":\"The characteristics of someone or something\",\"status"
                                        + "\":1,\"assignedto\":1,\"files\":\"Files\",\"ticket\":1,\"completeAt\":[1,1,1,1,1],\"completeUser\":1}}"));
    }

    /**
     * Method under test: {@link TicketController#getTicketBySprint(Integer)}
     */
    @Test
    void testGetTicketBySprint() throws Exception {
        TicketEntity ticketEntity = new TicketEntity();
        ticketEntity.onPreUpdate();
        ticketEntity.setAssignedTo(1);
        ticketEntity.setCategory(1);
        ticketEntity.setCompleteAt(LocalDateTime.of(1, 1, 1, 1, 1));
        ticketEntity.setCompleteUser(1);
        ticketEntity.setCreateAt(LocalDateTime.of(1, 1, 1, 1, 1));
        ticketEntity.setCreateUser(1);
        ticketEntity.setDescription("The characteristics of someone or something");
        ticketEntity.setId(1);
        ticketEntity.setName("Name");
        ticketEntity.setProject(1);
        ticketEntity.setSprint(1);
        ticketEntity.setStatus(1);
        ticketEntity.setUpdateAt(LocalDateTime.of(1, 1, 1, 1, 1));
        ticketEntity.setUpdateUser(1);
        ticketEntity.setWeignt(1);
        Optional<TicketEntity> ofResult = Optional.of(ticketEntity);
        when(ticketRepository.findById((Integer) any())).thenReturn(ofResult);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/v1/ticket/sprint/{id}", 1);
        MockMvcBuilders.standaloneSetup(ticketController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"code\":1,\"msg\":\"SUCCESS\",\"data\":{\"createAt\":[1,1,1,1,1],\"updateAt\":[1,1,1,1,1],\"createUser\":1,"
                                        + "\"updateUser\":1,\"id\":1,\"name\":\"Name\",\"description\":\"The characteristics of someone or something\","
                                        + "\"category\":1,\"weignt\":1,\"status\":1,\"completeAt\":[1,1,1,1,1],\"completeUser\":1,\"assignedTo\":1,\"sprint"
                                        + "\":1,\"project\":1}}"));
    }

    /**
     * Method under test: {@link TicketController#getTicketBySprint(Integer)}
     */
    @Test
    void testGetTicketBySprint2() throws Exception {
        when(ticketRepository.findById((Integer) any())).thenReturn(Optional.empty());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/v1/ticket/sprint/{id}", 1);
        MockMvcBuilders.standaloneSetup(ticketController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"code\":2,\"msg\":\"El ticket con id: 1 no existe.\"}"));
    }

    /**
     * Method under test: {@link TicketController#update(Integer, TicketRequestCreateDto)}
     */
    @Test
    void testUpdate() throws Exception {
        TicketEntity ticketEntity = new TicketEntity();
        ticketEntity.onPreUpdate();
        ticketEntity.setAssignedTo(1);
        ticketEntity.setCategory(1);
        ticketEntity.setCompleteAt(LocalDateTime.of(1, 1, 1, 1, 1));
        ticketEntity.setCompleteUser(1);
        ticketEntity.setCreateAt(LocalDateTime.of(1, 1, 1, 1, 1));
        ticketEntity.setCreateUser(1);
        ticketEntity.setDescription("The characteristics of someone or something");
        ticketEntity.setId(1);
        ticketEntity.setName("Name");
        ticketEntity.setProject(1);
        ticketEntity.setSprint(1);
        ticketEntity.setStatus(1);
        ticketEntity.setUpdateAt(LocalDateTime.of(1, 1, 1, 1, 1));
        ticketEntity.setUpdateUser(1);
        ticketEntity.setWeignt(1);
        Optional<TicketEntity> ofResult = Optional.of(ticketEntity);

        TicketEntity ticketEntity1 = new TicketEntity();
        ticketEntity1.onPreUpdate();
        ticketEntity1.setAssignedTo(1);
        ticketEntity1.setCategory(1);
        ticketEntity1.setCompleteAt(LocalDateTime.of(1, 1, 1, 1, 1));
        ticketEntity1.setCompleteUser(1);
        ticketEntity1.setCreateAt(LocalDateTime.of(1, 1, 1, 1, 1));
        ticketEntity1.setCreateUser(1);
        ticketEntity1.setDescription("The characteristics of someone or something");
        ticketEntity1.setId(1);
        ticketEntity1.setName("Name");
        ticketEntity1.setProject(1);
        ticketEntity1.setSprint(1);
        ticketEntity1.setStatus(1);
        ticketEntity1.setUpdateAt(LocalDateTime.of(1, 1, 1, 1, 1));
        ticketEntity1.setUpdateUser(1);
        ticketEntity1.setWeignt(1);
        when(ticketRepository.save((TicketEntity) any())).thenReturn(ticketEntity1);
        when(ticketRepository.findById((Integer) any())).thenReturn(ofResult);

        TicketRequestCreateDto ticketRequestCreateDto = new TicketRequestCreateDto();
        ticketRequestCreateDto.setAssignedTo(1);
        ticketRequestCreateDto.setCategory(1);
        ticketRequestCreateDto.setDescription("The characteristics of someone or something");
        ticketRequestCreateDto.setName("Name");
        ticketRequestCreateDto.setProject(1);
        ticketRequestCreateDto.setSprint(1);
        ticketRequestCreateDto.setStatus(1);
        ticketRequestCreateDto.setWeight(3);
        String content = (new ObjectMapper()).writeValueAsString(ticketRequestCreateDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/v1/ticket/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(ticketController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"code\":1,\"msg\":\"SUCCESS\",\"data\":{\"createAt\":[1,1,1,1,1],\"updateAt\":[1,1,1,1,1],\"createUser\":1,"
                                        + "\"updateUser\":1,\"id\":1,\"name\":\"Name\",\"description\":\"The characteristics of someone or something\","
                                        + "\"category\":1,\"weignt\":1,\"status\":1,\"completeAt\":[1,1,1,1,1],\"completeUser\":1,\"assignedTo\":1,\"sprint"
                                        + "\":1,\"project\":1}}"));
    }

    /**
     * Method under test: {@link TicketController#updateTask(Integer, TaskRequestCreateDto)}
     */
    @Test
    void testUpdateTask() throws Exception {
        TaskRequestCreateDto taskRequestCreateDto = new TaskRequestCreateDto();
        taskRequestCreateDto.setAssignedto(1);
        taskRequestCreateDto.setCompleteAt(LocalDateTime.of(1, 1, 1, 1, 1));
        taskRequestCreateDto.setCompleteUser(1);
        taskRequestCreateDto.setDescription("The characteristics of someone or something");
        taskRequestCreateDto.setFiles("Files");
        taskRequestCreateDto.setId(1);
        taskRequestCreateDto.setName("Name");
        taskRequestCreateDto.setStatus(1);
        taskRequestCreateDto.setTicket(1);
        String content = (new ObjectMapper()).writeValueAsString(taskRequestCreateDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/v1/ticket/task/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(ticketController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }

    /**
     * Method under test: {@link TicketController#updateTask(Integer, TaskRequestCreateDto)}
     */
    @Test
    void testUpdateTask2() throws Exception {
        TaskEntity taskEntity = new TaskEntity();
        taskEntity.onPreUpdate();
        taskEntity.setAssignedto(1);
        taskEntity.setCompleteAt(LocalDateTime.of(1, 1, 1, 1, 1));
        taskEntity.setCompleteUser(1);
        taskEntity.setCreateAt(LocalDateTime.of(1, 1, 1, 1, 1));
        taskEntity.setCreateUser(1);
        taskEntity.setDescription("The characteristics of someone or something");
        taskEntity.setFiles("Files");
        taskEntity.setId(1);
        taskEntity.setName("Name");
        taskEntity.setStatus(1);
        taskEntity.setTicket(1);
        taskEntity.setUpdateAt(LocalDateTime.of(1, 1, 1, 1, 1));
        taskEntity.setUpdateUser(1);
        Optional<TaskEntity> ofResult = Optional.of(taskEntity);

        TaskEntity taskEntity1 = new TaskEntity();
        taskEntity1.onPreUpdate();
        taskEntity1.setAssignedto(1);
        taskEntity1.setCompleteAt(LocalDateTime.of(1, 1, 1, 1, 1));
        taskEntity1.setCompleteUser(1);
        taskEntity1.setCreateAt(LocalDateTime.of(1, 1, 1, 1, 1));
        taskEntity1.setCreateUser(1);
        taskEntity1.setDescription("The characteristics of someone or something");
        taskEntity1.setFiles("Files");
        taskEntity1.setId(1);
        taskEntity1.setName("Name");
        taskEntity1.setStatus(1);
        taskEntity1.setTicket(1);
        taskEntity1.setUpdateAt(LocalDateTime.of(1, 1, 1, 1, 1));
        taskEntity1.setUpdateUser(1);
        when(taskRepository.save((TaskEntity) any())).thenReturn(taskEntity1);
        when(taskRepository.findById((Integer) any())).thenReturn(ofResult);

        TaskRequestCreateDto taskRequestCreateDto = new TaskRequestCreateDto();
        taskRequestCreateDto.setAssignedto(1);
        taskRequestCreateDto.setCompleteAt(null);
        taskRequestCreateDto.setCompleteUser(1);
        taskRequestCreateDto.setDescription("The characteristics of someone or something");
        taskRequestCreateDto.setFiles("Files");
        taskRequestCreateDto.setId(1);
        taskRequestCreateDto.setName("Name");
        taskRequestCreateDto.setStatus(1);
        taskRequestCreateDto.setTicket(1);
        String content = (new ObjectMapper()).writeValueAsString(taskRequestCreateDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/v1/ticket/task/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(ticketController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"code\":1,\"msg\":\"SUCCESS\",\"data\":{\"createAt\":[1,1,1,1,1],\"updateAt\":[1,1,1,1,1],\"createUser\":1,"
                                        + "\"updateUser\":1,\"id\":1,\"name\":\"Name\",\"description\":\"The characteristics of someone or something\",\"status"
                                        + "\":1,\"assignedto\":1,\"files\":\"Files\",\"ticket\":1,\"completeAt\":[1,1,1,1,1],\"completeUser\":1}}"));
    }
}

