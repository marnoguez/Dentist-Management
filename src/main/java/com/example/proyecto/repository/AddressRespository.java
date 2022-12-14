package com.example.proyecto.repository;

import com.example.proyecto.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRespository extends JpaRepository<Address, Long> {
}
