package com.Guinazu.TP_JPA;

import com.Guinazu.TP_JPA.entidades.*;
import com.Guinazu.TP_JPA.repositorio.*;
import com.Guinazu.TP_JPA.enumeraciones.EstadoPedido;
import com.Guinazu.TP_JPA.enumeraciones.FormaPago;
import com.Guinazu.TP_JPA.enumeraciones.TipoEnvio;
import com.Guinazu.TP_JPA.enumeraciones.TipoProducto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootApplication
public class TpJpaApplication {

	@Autowired
	ClienteRepositorio clienteRepositorio;
	@Autowired
	DomicilioRepositorio domicilioRepositorio;
	@Autowired
	DetallePedidoRepositorio detallePedidoRepositorio;
	@Autowired
	FacturaRepositorio facturaRepositorio;
	@Autowired
	PedidoRepositorio pedidoRepositorio;
	@Autowired
	ProductoRepositorio productoRepositorio;
	@Autowired
	RubroRepositorio rubroRepositorio;
	public static void main(String[] args) {
		SpringApplication.run(TpJpaApplication.class, args);}

	@Bean
	CommandLineRunner init(ClienteRepositorio clienteRepositorio){
		return args -> {
			System.out.println("----------- FUNCIONANDO ----------");

			Domicilio domicilio1 = Domicilio.builder()
					.calle("Rodriguez")
					.numero("2835")
					.localidad("Mendoza").
					build();

			Domicilio domicilio2 = Domicilio.builder()
					.calle("Jorge A. Calle")
					.numero("234")
					.localidad("Mendoza").
					build();

			Cliente cliente1 = Cliente.builder()
					.nombre("Tomás")
					.apellido("Guiñazu")
					.telefono("261 1234-567")
					.email("tomasguinazu@email.com")
					.build();

			cliente1.agregarDomicilio(domicilio1);
			cliente1.agregarDomicilio(domicilio2);

			clienteRepositorio.save(cliente1);
			Cliente clienteRecuperado = clienteRepositorio.findById(cliente1.getId()) .orElse(null);

			if (clienteRecuperado != null) {
				System.out.println("Nombre: " + clienteRecuperado.getNombre());
				System.out.println("Apellido: " + clienteRecuperado.getApellido());
				System.out.println("Teléfono: " + clienteRecuperado.getTelefono());
				System.out.println("Email: " + clienteRecuperado.getEmail());
				clienteRecuperado.mostrarDomicilios();
			}


			Producto producto1 = Producto.builder()
					.denominacion("Pizza napolitana")
					.unidadMedida("unidad")
					.precioCompra(700.0)
					.precioVenta(1400.0)
					.receta("masa, salsa de tomate, tomate, mozzarella, albaca")
					.stockActual(50)
					.stockMinimo(10)
					.tipoProducto(TipoProducto.MANUFACTURADO)
					.tiempoEstimadoCocina(30)
					.build();
			Producto producto2 = Producto.builder()
					.denominacion("Pizza especial")
					.unidadMedida("unidad")
					.precioCompra(800.0)
					.precioVenta(1600.0)
					.receta("masa, salsa de tomate, jamon, mozzarela, morron")
					.stockActual(30)
					.stockMinimo(7)
					.tipoProducto(TipoProducto.MANUFACTURADO)
					.tiempoEstimadoCocina(25)
					.build();

			Rubro rubro1 = Rubro.builder()
					.denominacion("Pizza")
					.build();

			rubro1.agregarProducto(producto1);
			rubro1.agregarProducto(producto2);

			rubroRepositorio.save(rubro1);

			Rubro rubroRecuperado = rubroRepositorio.findById(rubro1.getId()) .orElse(null);

			if (rubroRecuperado != null) {
				System.out.println("Denominación: " + rubroRecuperado.getDenominacion());
				rubroRecuperado.mostrarProducto();
			}

			DetallePedido detalle1 = DetallePedido.builder()
					.cantidad(4)
					.subtotal(4800.0)
					.build();
			DetallePedido detalle2 = DetallePedido.builder()
					.cantidad(1)
					.subtotal(1600.0)
					.build();


			detalle1.setProducto(producto2);
			detalle2.setProducto(producto1);

			SimpleDateFormat fechaFormato = new SimpleDateFormat("yyyy-MM-dd");
			String fechaString1 = "2023-08-02";
			String fechaString2 = "2023-06-29";
			Date fechaPedido1 = fechaFormato.parse(fechaString1);
			Date fechaPedido2 = fechaFormato.parse(fechaString2);

			Pedido pedido1 = Pedido.builder()
					.estadoPedido(EstadoPedido.ENTREGADO)
					.fecha(fechaPedido1)
					.tipoEnvio(TipoEnvio.TAKE_AWAY)
					.total(6400.0)
					.build();
			Pedido pedido2 = Pedido.builder()
					.estadoPedido(EstadoPedido.PREPARACION)
					.fecha(fechaPedido2)
					.tipoEnvio(TipoEnvio.DELIVERY)
					.total(6700.0)
					.build();

			pedido1.agregarDetalle(detalle1);
			pedido1.agregarDetalle(detalle2);


			Factura factura1 = Factura.builder()
					.numero(132)
					.fecha(fechaPedido1)
					.descuento(0.0)
					.total(6400)
					.formaPago(FormaPago.EFECTIVO)
					.build();
			Factura factura2 = Factura.builder()
					.numero(231)
					.fecha(fechaPedido2)
					.descuento(50.0)
					.total(3350)
					.formaPago(FormaPago.EFECTIVO)
					.build();

			pedido1.setFactura(factura1);
			pedido2.setFactura(factura2);


		};


	}

}
