package com.Guinazu.TP_JPA.repositorio;

import com.Guinazu.TP_JPA.entidades.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepositorio extends JpaRepository<Pedido,Long> {
}
