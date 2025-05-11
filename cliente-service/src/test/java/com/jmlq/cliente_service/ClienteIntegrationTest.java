package com.jmlq.cliente_service;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.jmlq.cliente_service.model.Cliente;
import com.jmlq.cliente_service.repository.ClienteRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@DisplayName("Integración: ClienteController")
class ClienteIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ClienteRepository clienteRepository;

    // Evita RabbitMQ
    @MockitoBean
    private RabbitTemplate rabbitTemplate;

    private Cliente clienteGuardado;

    @BeforeEach
    void setUp() {
        clienteRepository.deleteAll();

        // Creamos Cliente
        Cliente c = new Cliente();
        c.setNombre("Mauricio Lahuasi");
        c.setGenero("M");
        c.setEdad(44);
        c.setIdentificacion("1716134898");
        c.setDireccion("LA FLORESTA");
        c.setTelefono("0979352641");
        c.setContrasena("123456");
        c.setEstado(true);

        clienteGuardado = clienteRepository.save(c);
    }

    @Test
    @DisplayName("GET /clientes/{id} → 200 y payload con persona.nombre, persona.identificacion y estado")
    void cuandoClienteExiste_cuandoGetPorId_entoncesDevuelve200yCliente() throws Exception {
        mockMvc.perform(get("/clientes/{id}", clienteGuardado.getId())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                // validamos los campos dentro de "persona"
                .andExpect(jsonPath("$.persona.nombre").value("Mauricio Lahuasi"))
                .andExpect(jsonPath("$.persona.identificacion").value("1716134898"))
                // validamos campo a nivel raíz
                .andExpect(jsonPath("$.estado").value(true));
    }
}
