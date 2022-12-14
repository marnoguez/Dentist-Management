package com.example.proyecto.service;




import com.example.proyecto.entity.Dentist;
import com.example.proyecto.exception.ResourceNotFoundException;
import com.example.proyecto.exception.BadRequestException;
import com.example.proyecto.repository.DentistRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DentistService {

    private static final Logger LOGGER = Logger.getLogger(DentistService.class);
    private final DentistRepository dentistRepository;

    @Autowired
    public DentistService(DentistRepository dentistRepository) {
        this.dentistRepository = dentistRepository;
    }



    public Dentist registrar(Dentist dentist) {
        LOGGER.info("Initialized operation for registration of dentist with last name: "+
                dentist.getApellido());
        return dentistRepository.save(dentist);
    }




    public void eliminar(Long id) throws ResourceNotFoundException, BadRequestException{

        Optional<Dentist> dentistAEliminar= buscar(id);
        if (dentistAEliminar.isPresent()){
            dentistRepository.deleteById(id);
            LOGGER.warn("Delete operation initialized with" +
                    "id="+id);
        }
        else{
            throw new ResourceNotFoundException("Dentist to delete does not exist" +
                    " in database, failed to find with id= "+id);
        }

    }

    public Dentist actualizar(Dentist dentist) throws ResourceNotFoundException, BadRequestException {
        if(buscar(dentist.getId()).isEmpty())
            throw new ResourceNotFoundException("Does not exist dentist with ID " + dentist.getId());
        LOGGER.info("Update operation initialized with id="+
                dentist.getId());
        return dentistRepository.save(dentist);
    }



    public Optional<Dentist> buscar(Long id) throws BadRequestException {
        if(!dentistRepository.existsById(id))
            throw new BadRequestException("Does not exist dentist with ID " + id);
        LOGGER.info("Search operation initialized with id="+id);
        return dentistRepository.findById(id);
    }


    public List<Dentist> buscarTodos() {
        LOGGER.info("Initialized operation to list all dentists");
        return dentistRepository.findAll();
    }


}
