package com.Guinazu.TP_JPA.entidades;

import com.Guinazu.TP_JPA.enumeraciones.FormaPago;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "factura")
public class Factura extends BaseEntidad{
    private int numero;
    private Date fecha;
    private Double descuento;
    private FormaPago formaPago;
    private int total;

}
