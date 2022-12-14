package com.example.proyecto.repository;

import com.example.proyecto.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {


}
