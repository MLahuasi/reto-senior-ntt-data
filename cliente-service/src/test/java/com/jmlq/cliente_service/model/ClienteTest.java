package com.jmlq.cliente_service.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Prueba unitaria de la entidad Cliente:
 * Verifica getters y setters de todos los campos,
 * incluidos los heredados de Persona.
 */
@DisplayName("Cliente – pruebas de getters/setters")
class ClienteTest {

    @Test
    @DisplayName("Debe almacenar y recuperar correctamente todos los atributos")
    void shouldSetAndGetAllProperties() {
        // 1) Instancia vacía
        Cliente cliente = new Cliente();

        // 2) Asignamos valores a campos heredados de Persona
        cliente.setNombre("Mauricio Lahuasi");
        cliente.setGenero("M");
        cliente.setEdad(44);
        cliente.setIdentificacion("1716138498");
        cliente.setDireccion("La Floresta");
        cliente.setTelefono("0979352641");

        // 3) Asignamos valores a campos propios de Cliente
        cliente.setContrasena("123456");
        cliente.setEstado(Boolean.TRUE);

        // 4) Verificaciones
        assertAll("Verificar estado completo de Cliente",
                () -> assertEquals("Mauricio Lahuasi", cliente.getNombre(), "Nombre"),
                () -> assertEquals("M", cliente.getGenero(), "Género"),
                () -> assertEquals(44, cliente.getEdad(), "Edad"),
                () -> assertEquals("1716138498", cliente.getIdentificacion(), "Identificación"),
                () -> assertEquals("La Floresta", cliente.getDireccion(), "Dirección"),
                () -> assertEquals("0979352641", cliente.getTelefono(), "Teléfono"),
                () -> assertEquals("123456", cliente.getContrasena(), "Contrasena"),
                () -> assertTrue(cliente.getEstado(), "Estado"));
    }
}