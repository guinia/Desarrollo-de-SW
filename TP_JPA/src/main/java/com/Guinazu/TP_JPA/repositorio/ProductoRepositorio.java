package com.Guinazu.TP_JPA.repositorio;

import com.Guinazu.TP_JPA.entidades.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepositorio extends JpaRepository<Producto,Long> {
}
