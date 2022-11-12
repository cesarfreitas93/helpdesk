package pe.farell.go.backend.service;

import pe.farell.go.backend.model.dto.request.TicketRequestCreateDto;
import pe.farell.go.backend.model.dto.response.Content;
import pe.farell.go.backend.model.dto.response.Response;
import pe.farell.go.backend.model.dto.response.TicketDto;
import pe.farell.go.backend.model.entity.TicketEntity;

public interface Ticket {

    Response<TicketEntity> saveTicket(TicketRequestCreateDto request);

    Response<TicketEntity> updateTicket(Integer id, TicketRequestCreateDto request);

    Response<TicketEntity> getTicket(Integer id);

    Response<Content<TicketDto>> getTicketsByProject(Integer id, Integer sprint);

}
