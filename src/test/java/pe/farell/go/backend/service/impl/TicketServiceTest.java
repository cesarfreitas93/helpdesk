package pe.farell.go.backend.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
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
import pe.farell.go.backend.model.dto.request.TicketRequestCreateDto;
import pe.farell.go.backend.model.dto.response.Response;
import pe.farell.go.backend.model.entity.SprintEntity;
import pe.farell.go.backend.model.entity.TicketEntity;
import pe.farell.go.backend.repository.SprintRepository;
import pe.farell.go.backend.repository.TicketRepository;

@ContextConfiguration(classes = {TicketService.class})
@ExtendWith(SpringExtension.class)
class TicketServiceTest {
    @MockBean
    private ModelMapper modelMapper;

    @MockBean
    private SprintRepository sprintRepository;

    @MockBean
    private TicketRepository ticketRepository;

    @Autowired
    private TicketService ticketService;

    /**
     * Method under test: {@link TicketService#saveTicket(TicketRequestCreateDto)}
     */
    @Test
    void testSaveTicket() {
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
        Optional<SprintEntity> ofResult = Optional.of(sprintEntity);
        when(sprintRepository.findById((Integer) any())).thenReturn(ofResult);

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
        when(ticketRepository.save((TicketEntity) any())).thenReturn(ticketEntity);

        TicketRequestCreateDto ticketRequestCreateDto = new TicketRequestCreateDto();
        ticketRequestCreateDto.setAssignedTo(1);
        ticketRequestCreateDto.setCategory(1);
        ticketRequestCreateDto.setDescription("The characteristics of someone or something");
        ticketRequestCreateDto.setName("Name");
        ticketRequestCreateDto.setProject(1);
        ticketRequestCreateDto.setSprint(1);
        ticketRequestCreateDto.setStatus(1);
        ticketRequestCreateDto.setWeight(3);
        Response<TicketEntity> actualSaveTicketResult = ticketService.saveTicket(ticketRequestCreateDto);
        assertEquals(1, actualSaveTicketResult.getCode().intValue());
        assertEquals("SUCCESS", actualSaveTicketResult.getMsg());
        assertSame(ticketEntity, actualSaveTicketResult.getData());
        verify(modelMapper).map((Object) any(), (Object) any());
        verify(sprintRepository).findById((Integer) any());
        verify(ticketRepository).save((TicketEntity) any());
    }

    /**
     * Method under test: {@link TicketService#saveTicket(TicketRequestCreateDto)}
     */
    @Test
    void testSaveTicket2() {
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
        Optional<SprintEntity> ofResult = Optional.of(sprintEntity);
        when(sprintRepository.findById((Integer) any())).thenReturn(ofResult);
        when(ticketRepository.save((TicketEntity) any())).thenThrow(new ValidationException(1, "Mensaje"));

        TicketRequestCreateDto ticketRequestCreateDto = new TicketRequestCreateDto();
        ticketRequestCreateDto.setAssignedTo(1);
        ticketRequestCreateDto.setCategory(1);
        ticketRequestCreateDto.setDescription("The characteristics of someone or something");
        ticketRequestCreateDto.setName("Name");
        ticketRequestCreateDto.setProject(1);
        ticketRequestCreateDto.setSprint(1);
        ticketRequestCreateDto.setStatus(1);
        ticketRequestCreateDto.setWeight(3);
        assertThrows(ValidationException.class, () -> ticketService.saveTicket(ticketRequestCreateDto));
        verify(modelMapper).map((Object) any(), (Object) any());
        verify(sprintRepository).findById((Integer) any());
        verify(ticketRepository).save((TicketEntity) any());
    }

    /**
     * Method under test: {@link TicketService#saveTicket(TicketRequestCreateDto)}
     */
    @Test
    void testSaveTicket3() {
        doNothing().when(modelMapper).map((Object) any(), (Object) any());
        when(sprintRepository.findById((Integer) any())).thenReturn(Optional.empty());

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
        when(ticketRepository.save((TicketEntity) any())).thenReturn(ticketEntity);

        TicketRequestCreateDto ticketRequestCreateDto = new TicketRequestCreateDto();
        ticketRequestCreateDto.setAssignedTo(1);
        ticketRequestCreateDto.setCategory(1);
        ticketRequestCreateDto.setDescription("The characteristics of someone or something");
        ticketRequestCreateDto.setName("Name");
        ticketRequestCreateDto.setProject(1);
        ticketRequestCreateDto.setSprint(1);
        ticketRequestCreateDto.setStatus(1);
        ticketRequestCreateDto.setWeight(3);
        assertThrows(ValidationException.class, () -> ticketService.saveTicket(ticketRequestCreateDto));
        verify(sprintRepository).findById((Integer) any());
    }

    /**
     * Method under test: {@link TicketService#updateTicket(Integer, TicketRequestCreateDto)}
     */
    @Test
    void testUpdateTicket() {
        doNothing().when(modelMapper).map((Object) any(), (Object) any());

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
        Response<TicketEntity> actualUpdateTicketResult = ticketService.updateTicket(1, ticketRequestCreateDto);
        assertEquals(1, actualUpdateTicketResult.getCode().intValue());
        assertEquals("SUCCESS", actualUpdateTicketResult.getMsg());
        assertSame(ticketEntity1, actualUpdateTicketResult.getData());
        verify(modelMapper).map((Object) any(), (Object) any());
        verify(ticketRepository).save((TicketEntity) any());
        verify(ticketRepository).findById((Integer) any());
    }

    /**
     * Method under test: {@link TicketService#updateTicket(Integer, TicketRequestCreateDto)}
     */
    @Test
    void testUpdateTicket2() {
        doNothing().when(modelMapper).map((Object) any(), (Object) any());

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
        when(ticketRepository.save((TicketEntity) any())).thenThrow(new ValidationException(1, "Mensaje"));
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
        assertThrows(ValidationException.class, () -> ticketService.updateTicket(1, ticketRequestCreateDto));
        verify(modelMapper).map((Object) any(), (Object) any());
        verify(ticketRepository).save((TicketEntity) any());
        verify(ticketRepository).findById((Integer) any());
    }

    /**
     * Method under test: {@link TicketService#getTicket(Integer)}
     */
    @Test
    void testGetTicket() {
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
        Response<TicketEntity> actualTicket = ticketService.getTicket(1);
        assertEquals(1, actualTicket.getCode().intValue());
        assertEquals("SUCCESS", actualTicket.getMsg());
        assertSame(ticketEntity, actualTicket.getData());
        verify(ticketRepository).findById((Integer) any());
    }

    /**
     * Method under test: {@link TicketService#getTicket(Integer)}
     */
    @Test
    void testGetTicket2() {
        when(ticketRepository.findById((Integer) any())).thenReturn(Optional.empty());
        Response<TicketEntity> actualTicket = ticketService.getTicket(1);
        assertEquals(2, actualTicket.getCode().intValue());
        assertEquals("El ticket con id: 1 no existe.", actualTicket.getMsg());
        assertNull(actualTicket.getData());
        verify(ticketRepository).findById((Integer) any());
    }

    /**
     * Method under test: {@link TicketService#getTicket(Integer)}
     */
    @Test
    void testGetTicket3() {
        when(ticketRepository.findById((Integer) any())).thenThrow(new ValidationException(3, " "));
        assertThrows(ValidationException.class, () -> ticketService.getTicket(1));
        verify(ticketRepository).findById((Integer) any());
    }
}

