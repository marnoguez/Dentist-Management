package com.example.proyecto.entity;

import javax.persistence.*;
import java.time.LocalDate;
@Entity
@Table(name = "Appointment")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "patient_id", referencedColumnName = "id")
    private Patient patient;

    @ManyToOne()
    @JoinColumn(name = "dentist_id", referencedColumnName = "id")
    private Dentist dentist;

    @Column
    private LocalDate fecha;

    public Appointment() {
    }

    public Appointment(Long id, Patient patient, Dentist dentist, LocalDate fecha) {
        this.id = id;
        this.patient = patient;
        this.dentist = dentist;
        this.fecha = fecha;
    }

    public Appointment(Patient patient, Dentist dentist, LocalDate fecha) {
        this.patient = patient;
        this.dentist = dentist;
        this.fecha = fecha;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Dentist getDentist() {
        return dentist;
    }

    public void setDentist(Dentist dentist) {
        this.dentist = dentist;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
}
