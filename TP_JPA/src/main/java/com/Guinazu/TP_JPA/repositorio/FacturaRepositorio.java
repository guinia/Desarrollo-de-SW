package com.Guinazu.TP_JPA.repositorio;

import com.Guinazu.TP_JPA.entidades.Factura;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacturaRepositorio extends JpaRepository <Factura,Long> {
}
