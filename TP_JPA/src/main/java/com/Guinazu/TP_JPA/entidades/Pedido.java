package com.Guinazu.TP_JPA.entidades;

import com.Guinazu.TP_JPA.enumeraciones.EstadoPedido;
import com.Guinazu.TP_JPA.enumeraciones.TipoEnvio;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "pedido")
public class Pedido extends BaseEntidad{

    private EstadoPedido estadoPedido;
    private Date fecha;
    private TipoEnvio tipoEnvio;
    private Double total;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "pedido_id")
    private Factura factura;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "pedido_id")
    @Builder.Default

    private List<DetallePedido> detalles = new ArrayList<>();

    public void  agregarDetalle(DetallePedido detalle){
        detalles.add(detalle);
    }

    public void mostrarDetalles(){
        System.out.println("Detalles del pedido: ");
        for (DetallePedido detalle: detalles){
            System.out.println("Cantidad: " + detalle.getCantidad() + ", Subtotal: " + detalle.getSubtotal() );
        }
    }

}
