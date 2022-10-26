package pe.farell.go.backend.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.farell.go.backend.constant.EnumResponse;
import pe.farell.go.backend.exception.ValidationException;
import pe.farell.go.backend.model.dto.request.SprintRequestCreateDto;
import pe.farell.go.backend.model.dto.response.Response;
import pe.farell.go.backend.model.entity.SprintEntity;
import pe.farell.go.backend.repository.SprintRepository;
import pe.farell.go.backend.service.Sprint;
import pe.farell.go.backend.util.ResponseUtil;

import java.util.Objects;

@Service
public class SprintService implements Sprint {

    @Autowired
    private SprintRepository sprintRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public Response<SprintEntity> save(SprintRequestCreateDto request) {
        SprintEntity sprintEntity = new SprintEntity();
        SprintEntity entityExist = sprintRepository.findByName(request.getName());
        if (!Objects.isNull(entityExist)) {
            throw new ValidationException(EnumResponse.C003.getCode(), "El sprint que desea registrar ya existe con el mismo nombre. Intente con otro");
        }
        mapper.map(request, sprintEntity);
        sprintEntity.setBeginDate(request.getBeginDate().atStartOfDay());
        sprintEntity.setEndDate(request.getEndDate().atStartOfDay());
        return ResponseUtil.validateObj(sprintRepository.save(sprintEntity), null);
    }
}
