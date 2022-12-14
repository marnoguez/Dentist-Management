package com.example.proyecto.service;


import com.example.proyecto.entity.Address;
import com.example.proyecto.entity.Patient;
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
class PatientServiceTest {
    @Autowired
    private PatientService patientService;

    @Test
    @Order(1)
    public void guardarPatientTest(){
        Patient patientAGuardar= new Patient("Guillermina","Trueno"
        ,"5161", LocalDate.of(2022,11,28),
                new Address("Calle a",548,"Capital","Montevideo"),"prueba@gmail.com");
        Patient patientGuardado=patientService.guardarPatient(patientAGuardar);
        assertEquals(1L,patientGuardado.getId());
    }
    @Test
    @Order(2)
    public void buscarPatientPorIdTest(){
        Long idABuscar=1L;
        Optional<Patient> patientBuscado=patientService.buscarPatient(idABuscar);
        assertNotNull(patientBuscado.get());
    }
    @Test
    @Order(3)
    public void buscarPatientsTest(){
        List<Patient> patients= patientService.buscarTodosPatients();
        //por la cantidad de los patients
        Integer cantidadEsperada=1;
        assertEquals(cantidadEsperada,patients.size());
    }
    @Test
    @Order(4)
    public void actualizarPatientTest() throws BadRequestException, ResourceNotFoundException {
        Patient patientAActualizar= new Patient(1L,"Guillermina","Trueno"
                ,"5161", LocalDate.of(2022,11,28),
                new Address(1L,"Calle a",548,"Salta Capital","Salta"),"prueba@gmail.com");
        patientService.actualizarPatient(patientAActualizar);
        Optional<Patient> patientActualizado= patientService.buscarPatient(patientAActualizar.getId());
        assertEquals("Guillermina",patientActualizado.get().getNombre());
    }
    @Test
    @Order(5)
    public void eliminarPatientTest() throws BadRequestException, ResourceNotFoundException {
        Long idAEliminar=1L;
        patientService.eliminar(idAEliminar);
        Optional<Patient> patientEliminado=patientService.buscarPatient(idAEliminar);
        assertFalse(patientEliminado.isPresent());
    }
}



