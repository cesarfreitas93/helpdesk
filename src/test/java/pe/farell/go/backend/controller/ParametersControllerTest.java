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
import pe.farell.go.backend.repository.CategoryHuRepository;
import pe.farell.go.backend.repository.StatusRepository;
import pe.farell.go.backend.service.impl.ParametersService;

@ContextConfiguration(classes = {ParametersController.class, ParametersService.class})
@ExtendWith(SpringExtension.class)
class ParametersControllerTest {
    @MockBean
    private CategoryHuRepository categoryHuRepository;

    @Autowired
    private ParametersController parametersController;

    @MockBean
    private StatusRepository statusRepository;

    /**
     * Method under test: {@link ParametersController#getAllCategoriesHu()}
     */
    @Test
    void testGetAllCategoriesHu() throws Exception {
        when(categoryHuRepository.findAll()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/v1/parameter/category/all");
        MockMvcBuilders.standaloneSetup(parametersController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"code\":2,\"msg\":\"La consulta de categorÃ­as no retorno resultados\",\"data\":{\"content\":[]}}"));
    }

    /**
     * Method under test: {@link ParametersController#getAllCategoriesHu()}
     */
    @Test
    void testGetAllCategoriesHu2() throws Exception {
        when(categoryHuRepository.findAll()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/v1/parameter/category/all");
        getResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(parametersController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"code\":2,\"msg\":\"La consulta de categorÃ­as no retorno resultados\",\"data\":{\"content\":[]}}"));
    }

    /**
     * Method under test: {@link ParametersController#getAllStatusTask()}
     */
    @Test
    void testGetAllStatusTask() throws Exception {
        when(statusRepository.findAll()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/v1/parameter/status/all");
        MockMvcBuilders.standaloneSetup(parametersController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"code\":2,\"msg\":\"La consulta de estados no retorno resultados\",\"data\":{\"content\":[]}}"));
    }

    /**
     * Method under test: {@link ParametersController#getAllStatusTask()}
     */
    @Test
    void testGetAllStatusTask2() throws Exception {
        when(statusRepository.findAll()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/v1/parameter/status/all");
        getResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(parametersController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"code\":2,\"msg\":\"La consulta de estados no retorno resultados\",\"data\":{\"content\":[]}}"));
    }
}

