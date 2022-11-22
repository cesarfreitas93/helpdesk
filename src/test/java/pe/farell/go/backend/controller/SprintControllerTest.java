package pe.farell.go.backend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDate;

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
import pe.farell.go.backend.model.dto.request.SprintRequestCreateDto;
import pe.farell.go.backend.repository.SprintRepository;
import pe.farell.go.backend.service.impl.SprintService;

@ContextConfiguration(classes = {SprintController.class, SprintService.class, ModelMapper.class})
@ExtendWith(SpringExtension.class)
class SprintControllerTest {
    @Autowired
    private SprintController sprintController;

    @MockBean
    private SprintRepository sprintRepository;

    /**
     * Method under test: {@link SprintController#create(SprintRequestCreateDto)}
     */
    @Test
    void testCreate() throws Exception {
        SprintRequestCreateDto sprintRequestCreateDto = new SprintRequestCreateDto();
        sprintRequestCreateDto.setBeginDate(LocalDate.ofEpochDay(1L));
        sprintRequestCreateDto.setEndDate(LocalDate.ofEpochDay(1L));
        sprintRequestCreateDto.setName("Name");
        sprintRequestCreateDto.setStatus(1);
        String content = (new ObjectMapper()).writeValueAsString(sprintRequestCreateDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/v1/sprint")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(sprintController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(405));
    }
}

