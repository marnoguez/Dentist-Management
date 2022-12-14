package com.example.proyecto.service;

import com.example.proyecto.dto.AppointmentDTO;
import com.example.proyecto.entity.Dentist;
import com.example.proyecto.entity.Patient;
import com.example.proyecto.entity.Appointment;
import com.example.proyecto.repository.AppointmentRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {

    private static final Logger LOGGER = Logger.getLogger(AppointmentService.class);

    private AppointmentRepository appointmentRepository;

    @Autowired
    public AppointmentService(AppointmentRepository appointmentRepository){
        this.appointmentRepository = appointmentRepository;
    }


    public void eliminar(Long id){
        LOGGER.warn("Delete operation initialized with" +
                "id="+id);
        appointmentRepository.deleteById(id);
    }

    public AppointmentDTO guardar (AppointmentDTO appointment){
        Appointment appointmentAGuardar=appointmentDTOaAppointment(appointment);
        Appointment appointmentGuardado=appointmentRepository.save(appointmentAGuardar);
        LOGGER.info("Initialized operation for registration of appointment.");
        return appointmentAAppointmentDTO(appointmentGuardado);
    }


    public void actualizar(AppointmentDTO appointment){
        Appointment appointmentAActualizar=appointmentDTOaAppointment(appointment);
        LOGGER.info("Update operation initialized with id="+
                appointment.getId());
        appointmentRepository.save(appointmentAActualizar);
    }
    public Optional<AppointmentDTO> buscar(Long id){
        Optional<Appointment> appointmentBuscado=appointmentRepository.findById(id);
        if (appointmentBuscado.isPresent()){
            LOGGER.info("Search operation initialized with id="+id);
            return Optional.of(appointmentAAppointmentDTO(appointmentBuscado.get()));
        }
        else{
            LOGGER.warn("Search operation failed");
            return Optional.empty();
        }

    }
    public List<AppointmentDTO> buscarTodos(){
        List<Appointment>appointmentsEncontrados=appointmentRepository.findAll();
        List<AppointmentDTO> respuesta= new ArrayList<>();
        for (Appointment t:appointmentsEncontrados) {
            respuesta.add(appointmentAAppointmentDTO(t));
        }
        LOGGER.info("Initialized operation to list all appointments");
        return respuesta;
    }
    private AppointmentDTO appointmentAAppointmentDTO(Appointment appointment){

        AppointmentDTO respuesta=new AppointmentDTO();
        respuesta.setId(appointment.getId());
        respuesta.setFecha(appointment.getFecha());
        respuesta.setDentistId(appointment.getDentist().getId());
        respuesta.setPatientId(appointment.getPatient().getId());
        return respuesta;
    }
    private Appointment appointmentDTOaAppointment(AppointmentDTO appointmentDTO){
        Appointment appointment= new Appointment();
        Patient patient= new Patient();
        Dentist dentist= new Dentist();

        patient.setId(appointmentDTO.getPatientId());
        dentist.setId(appointmentDTO.getDentistId());
        appointment.setId(appointmentDTO.getId());
        appointment.setFecha(appointmentDTO.getFecha());

        appointment.setPatient(patient);
        appointment.setDentist(dentist);

        return appointment;
    }





}
