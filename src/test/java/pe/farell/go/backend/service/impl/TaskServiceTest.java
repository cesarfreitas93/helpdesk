package pe.farell.go.backend.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pe.farell.go.backend.exception.ValidationException;
import pe.farell.go.backend.model.dto.request.TaskRequestCreateDto;
import pe.farell.go.backend.model.dto.response.Response;
import pe.farell.go.backend.model.entity.TaskEntity;
import pe.farell.go.backend.repository.TaskRepository;

@ContextConfiguration(classes = {TaskService.class})
@ExtendWith(SpringExtension.class)
class TaskServiceTest {
    @MockBean
    private ModelMapper modelMapper;

    @MockBean
    private TaskRepository taskRepository;

    @Autowired
    private TaskService taskService;

    /**
     * Method under test: {@link TaskService#save(TaskRequestCreateDto)}
     */
    @Test
    void testSave() {
        doNothing().when(modelMapper).map((Object) any(), (Object) any());

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
        taskRequestCreateDto.setCompleteAt(LocalDateTime.of(1, 1, 1, 1, 1));
        taskRequestCreateDto.setCompleteUser(1);
        taskRequestCreateDto.setDescription("The characteristics of someone or something");
        taskRequestCreateDto.setFiles("Files");
        taskRequestCreateDto.setId(1);
        taskRequestCreateDto.setName("Name");
        taskRequestCreateDto.setStatus(1);
        taskRequestCreateDto.setTicket(1);
        Response<TaskEntity> actualSaveResult = taskService.save(taskRequestCreateDto);
        assertEquals(1, actualSaveResult.getCode().intValue());
        assertEquals("SUCCESS", actualSaveResult.getMsg());
        assertSame(taskEntity, actualSaveResult.getData());
        verify(modelMapper).map((Object) any(), (Object) any());
        verify(taskRepository).save((TaskEntity) any());
    }

    /**
     * Method under test: {@link TaskService#save(TaskRequestCreateDto)}
     */
    @Test
    void testSave2() {
        doNothing().when(modelMapper).map((Object) any(), (Object) any());
        when(taskRepository.save((TaskEntity) any())).thenThrow(new ValidationException(1, "Mensaje"));

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
        assertThrows(ValidationException.class, () -> taskService.save(taskRequestCreateDto));
        verify(modelMapper).map((Object) any(), (Object) any());
        verify(taskRepository).save((TaskEntity) any());
    }

    /**
     * Method under test: {@link TaskService#update(Integer, TaskRequestCreateDto)}
     */
    @Test
    void testUpdate() {
        doNothing().when(modelMapper).map((Object) any(), (Object) any());

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
        taskRequestCreateDto.setCompleteAt(LocalDateTime.of(1, 1, 1, 1, 1));
        taskRequestCreateDto.setCompleteUser(1);
        taskRequestCreateDto.setDescription("The characteristics of someone or something");
        taskRequestCreateDto.setFiles("Files");
        taskRequestCreateDto.setId(1);
        taskRequestCreateDto.setName("Name");
        taskRequestCreateDto.setStatus(1);
        taskRequestCreateDto.setTicket(1);
        Response<TaskEntity> actualUpdateResult = taskService.update(1, taskRequestCreateDto);
        assertEquals(1, actualUpdateResult.getCode().intValue());
        assertEquals("SUCCESS", actualUpdateResult.getMsg());
        assertSame(taskEntity1, actualUpdateResult.getData());
        verify(modelMapper).map((Object) any(), (Object) any());
        verify(taskRepository).save((TaskEntity) any());
        verify(taskRepository).findById((Integer) any());
    }

    /**
     * Method under test: {@link TaskService#update(Integer, TaskRequestCreateDto)}
     */
    @Test
    void testUpdate2() {
        doNothing().when(modelMapper).map((Object) any(), (Object) any());

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
        when(taskRepository.save((TaskEntity) any())).thenThrow(new ValidationException(1, "Mensaje"));
        when(taskRepository.findById((Integer) any())).thenReturn(ofResult);

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
        assertThrows(ValidationException.class, () -> taskService.update(1, taskRequestCreateDto));
        verify(modelMapper).map((Object) any(), (Object) any());
        verify(taskRepository).save((TaskEntity) any());
        verify(taskRepository).findById((Integer) any());
    }

    /**
     * Method under test: {@link TaskService#update(Integer, TaskRequestCreateDto)}
     */
    @Test
    void testUpdate3() {
        doNothing().when(modelMapper).map((Object) any(), (Object) any());

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
        when(taskRepository.findById((Integer) any())).thenReturn(Optional.empty());

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
        assertThrows(ValidationException.class, () -> taskService.update(1, taskRequestCreateDto));
        verify(taskRepository).findById((Integer) any());
    }

    /**
     * Method under test: {@link TaskService#getById(Integer)}
     */
    @Test
    void testGetById() {
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
        Response<TaskEntity> actualById = taskService.getById(1);
        assertEquals(1, actualById.getCode().intValue());
        assertEquals("SUCCESS", actualById.getMsg());
        assertSame(taskEntity, actualById.getData());
        verify(taskRepository).findById((Integer) any());
    }

    /**
     * Method under test: {@link TaskService#getById(Integer)}
     */
    @Test
    void testGetById2() {
        when(taskRepository.findById((Integer) any())).thenReturn(Optional.empty());
        assertThrows(ValidationException.class, () -> taskService.getById(1));
        verify(taskRepository).findById((Integer) any());
    }

    /**
     * Method under test: {@link TaskService#getById(Integer)}
     */
    @Test
    void testGetById3() {
        when(taskRepository.findById((Integer) any()))
                .thenThrow(new ValidationException(1, "La tarea que desea abrir no existe."));
        assertThrows(ValidationException.class, () -> taskService.getById(1));
        verify(taskRepository).findById((Integer) any());
    }
}

