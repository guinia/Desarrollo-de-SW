package com.Guinazu.TP_JPA.repositorio;

import com.Guinazu.TP_JPA.entidades.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepositorio extends JpaRepository<Cliente,Long> {

}
