package pe.farell.go.backend.controller;

import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pe.farell.go.backend.repository.PersonRepository;
import pe.farell.go.backend.service.impl.PersonService;

@ContextConfiguration(classes = {PersonController.class, PersonService.class})
@ExtendWith(SpringExtension.class)
class PersonControllerTest {
    @Autowired
    private PersonController personController;

    @MockBean
    private PersonRepository personRepository;

    /**
     * Method under test: {@link PersonController#bloqueoTarjeta()}
     */
    @Test
    void testBloqueoTarjeta() throws Exception {
        when(personRepository.findAll()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/v1/person/all");
        MockMvcBuilders.standaloneSetup(personController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"code\":2,\"msg\":\"La consulta de personas no retorno resultados\",\"data\":{\"content\":[]}}"));
    }

    /**
     * Method under test: {@link PersonController#bloqueoTarjeta()}
     */
    @Test
    void testBloqueoTarjeta2() throws Exception {
        when(personRepository.findAll()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/v1/person/all");
        getResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(personController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"code\":2,\"msg\":\"La consulta de personas no retorno resultados\",\"data\":{\"content\":[]}}"));
    }
}

