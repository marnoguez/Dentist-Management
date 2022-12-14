package com.example.proyecto.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Dentist")
public class Dentist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String Matricula;
    @Column
    private String nombre;
    @Column
    private String apellido;

    @OneToMany(mappedBy = "dentist", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Appointment> appointment = new HashSet<>();

    public Dentist(){

    }

    public Dentist(Long id, String matricula, String nombre, String apellido) {
        this.id = id;
        Matricula = matricula;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public Dentist(String matricula, String nombre, String apellido) {
        Matricula = matricula;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMatricula() {
        return Matricula;
    }

    public void setMatricula(String matricula) {
        Matricula = matricula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    @Override
    public String toString() {
        return "Dentist{" +
                "id=" + id +
                ", numeroMatricula=" + Matricula +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                '}';
    }


}
