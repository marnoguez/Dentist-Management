package com.example.proyecto.controller;


import com.example.proyecto.dto.AppointmentDTO;
import com.example.proyecto.exception.BadRequestException;
import com.example.proyecto.service.DentistService;
import com.example.proyecto.service.PatientService;
import com.example.proyecto.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    private AppointmentService appointmentService;
    private PatientService patientService;
    private DentistService dentistService;



    @Autowired
    public AppointmentController(AppointmentService appointmentService, PatientService patientService, DentistService dentistService) {
        this.appointmentService = appointmentService;
        this.patientService = patientService;
        this.dentistService = dentistService;
    }



    @GetMapping
    public ResponseEntity<List<AppointmentDTO>> listarLosAppointments(){
        return ResponseEntity.ok(appointmentService.buscarTodos());
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<AppointmentDTO> buscarAppointment(@PathVariable("id") Long id){
        //tengo dos alternativas.
        Optional<AppointmentDTO> appointmentBuscado=appointmentService.buscar(id);
        if (appointmentBuscado.isPresent()){
            return ResponseEntity.ok(appointmentBuscado.get());
        }
        else{
            //no existe el appointment con el id ingresado
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<AppointmentDTO> guardar(@RequestBody AppointmentDTO appointmentDTO) throws BadRequestException {

        ResponseEntity<AppointmentDTO> respuesta;
        System.out.println(patientService.buscarPatient(appointmentDTO.getPatientId()).get());
        if (patientService.buscarPatient(appointmentDTO.getPatientId()).isPresent()&&
                dentistService.buscar(appointmentDTO.getDentistId()).isPresent()
        ){
            //ambos existen en la BD
            //podemos registrar el appointment sin problemas, indicamos ok (200)
            respuesta=ResponseEntity.ok(appointmentService.guardar(appointmentDTO));

        }
        else{
            //uno o ambos no existen, debemos bloquear la operación
            throw new BadRequestException("Cannot register appointment when " +
                    "dentist and/or patient does not exist");

        }
        return respuesta;

    }
    @PutMapping
    public ResponseEntity<String> actualizarAppointment(@RequestBody AppointmentDTO appointment) throws BadRequestException {
        //verificar que el appointment exista
        //control como el post

        ResponseEntity<AppointmentDTO> respuesta;

        if(appointmentService.buscar(appointment.getId()).isPresent()){
            //es un id válido
            if (patientService.buscarPatient(appointment.getPatientId()).isPresent()&&
                    dentistService.buscar(appointment.getDentistId()).isPresent()
            ){
                //ambos existen en la BD
                //podemos registrar el appointment sin problemas, indicamos ok (200)
                appointmentService.actualizar(appointment);
                return ResponseEntity.ok("Updated appointment with id= "+appointment.getId());
            }
            else{
                //uno o ambos no existen, debemos bloquear la operación
                return ResponseEntity.badRequest().body("Update failed, verify if" +
                        " dentist and/or patient exist in database");
            }
        }
        else{
            //error con el id
            return ResponseEntity.badRequest().body("Cannot update appointment" +
                    " if it does not exist in database.");
        }
    }
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarAppointment(@PathVariable Long id){
        if (appointmentService.buscar(id).isPresent()){
            appointmentService.eliminar(id);
            return ResponseEntity.ok().body("Deleted appointment with id= "+id);
        }
        else{
            return ResponseEntity.badRequest().body("Cannot delete appointment with id= "+id+
                    " if it does not exist in database.");
        }
    }
}
