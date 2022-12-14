package com.example.proyecto.controller;


import com.example.proyecto.entity.Patient;

import com.example.proyecto.exception.BadRequestException;
import com.example.proyecto.exception.ResourceNotFoundException;
import com.example.proyecto.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/patient")
public class PatientController {

    @Autowired
    private PatientService patientService;



    @PostMapping
    public ResponseEntity<Patient> registrarNuevoPatient(@RequestBody Patient patient){
        return ResponseEntity.ok(patientService.guardarPatient(patient));
    }
    @PutMapping
    public ResponseEntity<String> actualizarPatient(@RequestBody Patient patient) throws BadRequestException, ResourceNotFoundException {
        Optional<Patient> patientBuscado=patientService.buscarPatient(patient.getId());
        if (patientBuscado.isPresent()){
            patientService.actualizarPatient(patient);
            return ResponseEntity.ok("Updated patient with last name: "+patient.getApellido());
        }
        else{
            return ResponseEntity.badRequest().body("Patient with id= "+patient.getId()+" does not exist in database." +
                    "Cannot update something that does not exist :(");
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Patient>> buscarPatient(@PathVariable("id") Long id) throws BadRequestException {

        return ResponseEntity.ok(patientService.buscarPatient(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarPatient(@PathVariable("id") Long id) throws BadRequestException, ResourceNotFoundException {
        patientService.eliminar(id);
        return ResponseEntity.ok("Patient deleted successfully");
    }

    @GetMapping
    public ResponseEntity<List<Patient>> buscarTodosLosPatients() {
        return ResponseEntity.ok(patientService.buscarTodosPatients());
    }

}
