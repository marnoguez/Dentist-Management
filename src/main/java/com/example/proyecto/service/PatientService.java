package com.example.proyecto.service;



import com.example.proyecto.entity.Patient;

import com.example.proyecto.exception.BadRequestException;
import com.example.proyecto.exception.ResourceNotFoundException;
import com.example.proyecto.repository.PatientRepository;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;


@Service
public class PatientService {

    private static final Logger LOGGER = Logger.getLogger(PatientService.class);


    @Autowired
    private PatientRepository patientRepository;

    public Patient guardarPatient( Patient patient){
        LOGGER.info("Patient admission requested");

        return patientRepository.save(patient);
    }

    public List<Patient> buscarTodosPatients(){

        LOGGER.info("Initialized operation to list all patients");
        return patientRepository.findAll();
    }


    public Optional<Patient> buscarPatient(Long id) {
        LOGGER.info("Search operation initialized with id="+id);
        return patientRepository.findById(id);
    }

    public void eliminar(Long id) throws ResourceNotFoundException,BadRequestException {
        if (buscarPatient(id).isEmpty())
            throw new ResourceNotFoundException("Does not exist patient with ID " + id);
        LOGGER.warn("Delete operation initialized with" +
                "id="+id);
        patientRepository.deleteById(id);
    }

    public Patient actualizarPatient (Patient patient) throws ResourceNotFoundException, BadRequestException{
        if(buscarPatient(patient.getId()).isEmpty())
            throw new ResourceNotFoundException("Does not exist patient with ID " + patient.getId());
        LOGGER.info("Update operation initialized with id="+
                patient.getId());
        return patientRepository.save(patient);

    }


}
