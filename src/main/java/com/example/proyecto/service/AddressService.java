package com.example.proyecto.service;
import com.example.proyecto.repository.AddressRespository;

import com.example.proyecto.entity.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {

    private final AddressRespository domicilioRepository;

    @Autowired
    public AddressService(AddressRespository domicilioRepository) {
        this.domicilioRepository = domicilioRepository;
    }

    public Address guardar(Address d){
        domicilioRepository.save(d);
        return d;
    }
    public Optional<Address> buscar(Long id){
        return Optional.of(domicilioRepository.getOne(Long.valueOf(id)));
    }
    public List<Address> buscarTodos(){
        return domicilioRepository.findAll();
    }
    public void eliminar(Long id){
        domicilioRepository.deleteById(Long.valueOf(id));
    }
}
