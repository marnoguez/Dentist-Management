package com.example.proyecto.controller;


import com.example.proyecto.entity.Dentist;
import com.example.proyecto.exception.BadRequestException;
import com.example.proyecto.exception.ResourceNotFoundException;
import com.example.proyecto.service.DentistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

//rest o no rest
@RestController
@RequestMapping("/dentists")
public class DentistController {
    private DentistService dentistService;

    @Autowired
    public DentistController( DentistService dentistService) {
        this.dentistService = dentistService;
    }



    @PostMapping
    public ResponseEntity<Dentist> guardarDentist(@RequestBody Dentist dentist) {
        return ResponseEntity.ok(dentistService.registrar(dentist));
    }

    @PutMapping
    public ResponseEntity<String> actualizarDentist(@RequestBody Dentist dentist) throws BadRequestException {
        if (dentistService.buscar(dentist.getId()) != null) {
            dentistService.registrar(dentist);
            return ResponseEntity.ok("Dentist updated in database");
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Dentist>> buscarDentist(@PathVariable("id") Long id) throws BadRequestException {
        return ResponseEntity.ok(dentistService.buscar(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarDentist(@PathVariable("id") Long id) throws ResourceNotFoundException, BadRequestException {
        dentistService.eliminar(id);
        return ResponseEntity.ok("Dentist deleted successfully");
    }

    @GetMapping
    public List<Dentist> buscarTodosLosDentists() {
        return dentistService.buscarTodos();
    }
}
