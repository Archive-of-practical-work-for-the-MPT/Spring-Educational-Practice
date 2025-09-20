package com.example.project2;

import com.example.project2.controller.api.AircraftApiController;
import com.example.project2.model.Aircraft;
import com.example.project2.service.AircraftService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.security.test.context.support.WithMockUser;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(AircraftApiController.class)
@Import(com.example.project2.config.SecurityConfig.class) // Импортируем конфигурацию безопасности
public class AircraftApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AircraftService aircraftService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @WithMockUser(roles = "ADMIN") // Эмулируем аутентифицированного пользователя с ролью администратора
    public void testCreateAircraft() throws Exception {
        Aircraft aircraft = new Aircraft();
        aircraft.setId(1L);
        aircraft.setModel("Boeing 737");
        aircraft.setSerialNumber("B737-1234");
        aircraft.setCapacity(180);
        aircraft.setManufacturer("Boeing");

        when(aircraftService.createAircraft(any(Aircraft.class))).thenReturn(aircraft);

        mockMvc.perform(post("/api/aircrafts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"model\":\"Boeing 737\",\"serialNumber\":\"B737-1234\",\"capacity\":180,\"manufacturer\":\"Boeing\"}"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.model").value("Boeing 737"))
                .andExpect(jsonPath("$.serialNumber").value("B737-1234"))
                .andExpect(jsonPath("$.capacity").value(180))
                .andExpect(jsonPath("$.manufacturer").value("Boeing"));
    }
}