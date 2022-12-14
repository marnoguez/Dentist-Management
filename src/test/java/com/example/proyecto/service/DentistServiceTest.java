package com.example.proyecto.service;

import com.example.proyecto.entity.Address;
import com.example.proyecto.entity.Dentist;

import com.example.proyecto.exception.BadRequestException;
import com.example.proyecto.exception.ResourceNotFoundException;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
class DentistServiceTest {
    @Autowired
    private DentistService dentistService;

    @Test
    @Order(1)
    public void registrarDentistTest() {
        Dentist dentistARegistrar = new Dentist("53285237","Juan Pablo", "Nocetti");
        Dentist dentistRegistrado = dentistService.registrar(dentistARegistrar);
        assertEquals(1L, dentistRegistrado.getId());
    }

    @Test
    @Order(2)
    public void buscarDentistPorIdTest() throws BadRequestException {
        Long idABuscar = 1L;
        Optional<Dentist> dentistBuscado = dentistService.buscar(idABuscar);
        assertNotNull(dentistBuscado.get());
    }

    @Test
    @Order(3)
    public void buscarDentistsTest() {
        List<Dentist> dentists = dentistService.buscarTodos();
        //por la cantidad de los dentists
        Integer cantidadEsperada = 1;
        assertEquals(cantidadEsperada, dentists.size());
    }

    @Test
    @Order(4)
    public void actualizarDentistTest() throws BadRequestException, ResourceNotFoundException {
        Dentist dentistAActualizar = new Dentist("89246754", "Juan Pablo", "Nocetti");
        dentistService.registrar(dentistAActualizar);
        dentistService.actualizar(dentistAActualizar);
        Optional<Dentist> dentistActualizado = dentistService.buscar(dentistAActualizar.getId());
        assertEquals("89246754", dentistActualizado.get().getMatricula());
    }

    @Test
    @Order(5)
    public void eliminarDentistTest() throws BadRequestException, ResourceNotFoundException {
        Long idAEliminar = 1L;
        dentistService.eliminar(idAEliminar);

        BadRequestException thrown = assertThrows(
                BadRequestException.class,
                () -> dentistService.buscar(idAEliminar)
        );

        assertTrue(thrown.getMessage().contains("Does not exist dentist with ID "+idAEliminar));


    }
}