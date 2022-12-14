package com.example.proyecto.security;


import com.example.proyecto.entity.User;
import com.example.proyecto.entity.UserRole;
import com.example.proyecto.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CargarDatosIniciales implements ApplicationRunner {
    private UserRepository usuarioRepository;
    @Autowired
    public CargarDatosIniciales(UserRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }



    @Override
    public void run(ApplicationArguments args) throws Exception {
        BCryptPasswordEncoder cifrador = new BCryptPasswordEncoder();
        BCryptPasswordEncoder cifradorAdmin = new BCryptPasswordEncoder();

        String passCifrada = cifrador.encode("1234");
        String passCifradaAdmin = cifradorAdmin.encode("12341234");

        User usuario = new User("Marcela", "marcela", "marcela@gmail.com", passCifrada, UserRole.ROLE_USER);
        usuarioRepository.save(usuario);

        User usuarioAdmin = new User("Martina", "marnoguez", "martina@gmail.com", passCifradaAdmin, UserRole.ROLE_ADMIN);
        usuarioRepository.save(usuarioAdmin);
    }



}
