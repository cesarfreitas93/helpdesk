package pe.farell.go.backend.service.impl;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pe.farell.go.backend.exception.ValidationException;
import pe.farell.go.backend.model.dto.request.SprintRequestCreateDto;
import pe.farell.go.backend.model.entity.SprintEntity;
import pe.farell.go.backend.repository.SprintRepository;

@ContextConfiguration(classes = {SprintService.class})
@ExtendWith(SpringExtension.class)
class SprintServiceTest {
    @MockBean
    private ModelMapper modelMapper;

    @MockBean
    private SprintRepository sprintRepository;

    @Autowired
    private SprintService sprintService;

    /**
     * Method under test: {@link SprintService#save(SprintRequestCreateDto)}
     */
    @Test
    void testSave() {
        doNothing().when(modelMapper).map((Object) any(), (Object) any());

        SprintEntity sprintEntity = new SprintEntity();
        sprintEntity.onPreUpdate();
        sprintEntity.setBeginDate(LocalDateTime.of(1, 1, 1, 1, 1));
        sprintEntity.setCreateAt(LocalDateTime.of(1, 1, 1, 1, 1));
        sprintEntity.setCreateUser(1);
        sprintEntity.setEndDate(LocalDateTime.of(1, 1, 1, 1, 1));
        sprintEntity.setId(1);
        sprintEntity.setName("Name");
        sprintEntity.setStatus(1);
        sprintEntity.setUpdateAt(LocalDateTime.of(1, 1, 1, 1, 1));
        sprintEntity.setUpdateUser(1);

        SprintEntity sprintEntity1 = new SprintEntity();
        sprintEntity1.onPreUpdate();
        sprintEntity1.setBeginDate(LocalDateTime.of(1, 1, 1, 1, 1));
        sprintEntity1.setCreateAt(LocalDateTime.of(1, 1, 1, 1, 1));
        sprintEntity1.setCreateUser(1);
        sprintEntity1.setEndDate(LocalDateTime.of(1, 1, 1, 1, 1));
        sprintEntity1.setId(1);
        sprintEntity1.setName("Name");
        sprintEntity1.setStatus(1);
        sprintEntity1.setUpdateAt(LocalDateTime.of(1, 1, 1, 1, 1));
        sprintEntity1.setUpdateUser(1);
        when(sprintRepository.save((SprintEntity) any())).thenReturn(sprintEntity);
        when(sprintRepository.findByName((String) any())).thenReturn(sprintEntity1);

        SprintRequestCreateDto sprintRequestCreateDto = new SprintRequestCreateDto();
        sprintRequestCreateDto.setBeginDate(LocalDate.ofEpochDay(1L));
        sprintRequestCreateDto.setEndDate(LocalDate.ofEpochDay(1L));
        sprintRequestCreateDto.setName("Name");
        sprintRequestCreateDto.setStatus(1);
        assertThrows(ValidationException.class, () -> sprintService.save(sprintRequestCreateDto));
        verify(sprintRepository).findByName((String) any());
    }

    /**
     * Method under test: {@link SprintService#save(SprintRequestCreateDto)}
     */
    @Test
    void testSave2() {
        doNothing().when(modelMapper).map((Object) any(), (Object) any());
        when(sprintRepository.save((SprintEntity) any())).thenThrow(new ValidationException(1, "Mensaje"));
        when(sprintRepository.findByName((String) any())).thenThrow(new ValidationException(1, "Mensaje"));

        SprintRequestCreateDto sprintRequestCreateDto = new SprintRequestCreateDto();
        sprintRequestCreateDto.setBeginDate(LocalDate.ofEpochDay(1L));
        sprintRequestCreateDto.setEndDate(LocalDate.ofEpochDay(1L));
        sprintRequestCreateDto.setName("Name");
        sprintRequestCreateDto.setStatus(1);
        assertThrows(ValidationException.class, () -> sprintService.save(sprintRequestCreateDto));
        verify(sprintRepository).findByName((String) any());
    }

    /**
     * Method under test: {@link SprintService#save(SprintRequestCreateDto)}
     */
    @Test
    void testSave3() {
        doNothing().when(modelMapper).map((Object) any(), (Object) any());

        SprintEntity sprintEntity = new SprintEntity();
        sprintEntity.onPreUpdate();
        sprintEntity.setBeginDate(LocalDateTime.of(1, 1, 1, 1, 1));
        sprintEntity.setCreateAt(LocalDateTime.of(1, 1, 1, 1, 1));
        sprintEntity.setCreateUser(1);
        sprintEntity.setEndDate(LocalDateTime.of(1, 1, 1, 1, 1));
        sprintEntity.setId(1);
        sprintEntity.setName("Name");
        sprintEntity.setStatus(1);
        sprintEntity.setUpdateAt(LocalDateTime.of(1, 1, 1, 1, 1));
        sprintEntity.setUpdateUser(1);

        SprintEntity sprintEntity1 = new SprintEntity();
        sprintEntity1.onPreUpdate();
        sprintEntity1.setBeginDate(LocalDateTime.of(1, 1, 1, 1, 1));
        sprintEntity1.setCreateAt(LocalDateTime.of(1, 1, 1, 1, 1));
        sprintEntity1.setCreateUser(1);
        sprintEntity1.setEndDate(LocalDateTime.of(1, 1, 1, 1, 1));
        sprintEntity1.setId(1);
        sprintEntity1.setName("Name");
        sprintEntity1.setStatus(1);
        sprintEntity1.setUpdateAt(LocalDateTime.of(1, 1, 1, 1, 1));
        sprintEntity1.setUpdateUser(1);
        when(sprintRepository.save((SprintEntity) any())).thenReturn(sprintEntity);
        when(sprintRepository.findByName((String) any())).thenReturn(sprintEntity1);
        SprintRequestCreateDto sprintRequestCreateDto = mock(SprintRequestCreateDto.class);
        when(sprintRequestCreateDto.getName()).thenReturn("Name");
        doNothing().when(sprintRequestCreateDto).setBeginDate((LocalDate) any());
        doNothing().when(sprintRequestCreateDto).setEndDate((LocalDate) any());
        doNothing().when(sprintRequestCreateDto).setName((String) any());
        doNothing().when(sprintRequestCreateDto).setStatus((Integer) any());
        sprintRequestCreateDto.setBeginDate(LocalDate.ofEpochDay(1L));
        sprintRequestCreateDto.setEndDate(LocalDate.ofEpochDay(1L));
        sprintRequestCreateDto.setName("Name");
        sprintRequestCreateDto.setStatus(1);
        assertThrows(ValidationException.class, () -> sprintService.save(sprintRequestCreateDto));
        verify(sprintRepository).findByName((String) any());
        verify(sprintRequestCreateDto).getName();
        verify(sprintRequestCreateDto).setBeginDate((LocalDate) any());
        verify(sprintRequestCreateDto).setEndDate((LocalDate) any());
        verify(sprintRequestCreateDto).setName((String) any());
        verify(sprintRequestCreateDto).setStatus((Integer) any());
    }
}

