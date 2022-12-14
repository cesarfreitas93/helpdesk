package pe.farell.go.backend.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.farell.go.backend.constant.EnumResponse;
import pe.farell.go.backend.exception.ValidationException;
import pe.farell.go.backend.model.dto.request.TicketRequestCreateDto;
import pe.farell.go.backend.model.dto.response.Response;
import pe.farell.go.backend.model.entity.SprintEntity;
import pe.farell.go.backend.model.entity.TicketEntity;
import pe.farell.go.backend.repository.SprintRepository;
import pe.farell.go.backend.repository.TicketRepository;
import pe.farell.go.backend.service.Ticket;
import pe.farell.go.backend.util.ResponseUtil;
import pe.farell.go.backend.util.StringUtil;

import java.util.Optional;

@Slf4j
@Service
public class TicketService implements Ticket {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private SprintRepository sprintRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public Response<TicketEntity> saveTicket(TicketRequestCreateDto request) {

        Optional<SprintEntity> optionalSprint = sprintRepository.findById(request.getSprint());
        if (!optionalSprint.isPresent()) {
            throw new ValidationException(EnumResponse.C003.getCode(), "El sprint al que desea asociar este ticket no existe.");
        }
        TicketEntity entity = new TicketEntity();
        mapper.map(request, entity);
        TicketEntity ticketEntity = ticketRepository.save(entity);
        return ResponseUtil.validateObj(ticketEntity, null);

    }

    @Override
    public Response<TicketEntity> updateTicket(Integer id, TicketRequestCreateDto request) {
        Optional<TicketEntity> entity = ticketRepository.findById(id);
        if (!entity.isPresent()) {
            String msgNull = StringUtil.concatenarCadenas(" ","El ticket con id:", id.toString(),"no existe.");
            throw new ValidationException(EnumResponse.C003.getCode(), msgNull);
        }
        TicketEntity ticketEntity = entity.get();
        mapper.map(request, ticketEntity);
        return ResponseUtil.validateObj(ticketRepository.save(ticketEntity), null);
    }

    @Override
    public Response<TicketEntity> getTicket(Integer id) {
        Optional<TicketEntity> entity = ticketRepository.findById(id);
        if (!entity.isPresent()) {
            String msgNull = StringUtil.concatenarCadenas(" ","El ticket con id:", id.toString(),"no existe.");
            return ResponseUtil.validateObj(null, msgNull);
        }
        return ResponseUtil.validateObj(entity.get(), null);
    }
}
